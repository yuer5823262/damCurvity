package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.common.Constant;
import com.example.damcurvity.entity.BaseAcquisitionEqu;
import com.example.damcurvity.entity.BaseEquipment;
import com.example.damcurvity.entity.BaseStation;
import com.example.damcurvity.entity.BaseStationNode;
import com.example.damcurvity.exception.DamPourException;
import com.example.damcurvity.exception.DampouringExceptionEnum;
import com.example.damcurvity.req.CmdReq;
import com.example.damcurvity.service.BaseAcquisitionEquService;
import com.example.damcurvity.service.BaseStationNodeService;
import com.example.damcurvity.service.BaseStationService;
import com.example.damcurvity.util.DeviceUtils;
import io.swagger.annotations.Api;
import com.example.damcurvity.util.JwtUtils;
import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.service.BaseEquipmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.damcurvity.que.BaseQue;
import com.example.damcurvity.util.ExcelUtil;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (BaseEquipment)表控制层
 *
 * @author makejava
 * @since 2023-08-07 16:19:36
 */
@Api("baseEquipment")
@RestController
@RequestMapping("baseEquipment")
public class BaseEquipmentController {
    /**
     * 服务对象
     */
    @Resource
    private BaseEquipmentService baseEquipmentService;
    @Resource
    private BaseAcquisitionEquService baseAcquisitionEquService;
    @Resource
    private BaseStationNodeService baseStationNodeService;
    @Resource
    private BaseStationService baseStationService;

    @GetMapping("select")
    public ApiRestResponse selectAll(BaseQue baseQue, BaseEquipment baseEquipment) {

        Page<BaseEquipment> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
        return ApiRestResponse.success(this.baseEquipmentService.page(page, new QueryWrapper<>(baseEquipment)));
    }
    @GetMapping("selectList")
    public ApiRestResponse selectList(BaseQue baseQue, BaseEquipment baseEquipment) {

        Page<BaseEquipment> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
        return ApiRestResponse.success(this.baseEquipmentService.selectListJoin(page, baseEquipment));
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
            return ApiRestResponse.success(this.baseEquipmentService.getById(id));
    }
    @PostMapping("cmd")

    public ApiRestResponse cmd(@RequestBody CmdReq baseEquipment)
    {

        Constant.logger.info("接收到对设备：" + baseEquipment.getName()+ " 的命令：" + baseEquipment.getCmd());
        BaseAcquisitionEqu baseAcquisitionEqu = baseAcquisitionEquService.getById(baseEquipment.getAcquId());
        DeviceUtils deviceUtils = DeviceUtils.getDeviceUtil(baseAcquisitionEqu.getDk());
        if(!deviceUtils.portIsOpen()) return ApiRestResponse.success("串口未开");
        String result = deviceUtils.sendMessageAndGetResult(baseEquipment.getCmd());
        if(result==null)
        {
            return ApiRestResponse.success("发生错误");
        }
        Constant.logger.info("返回对设备：" + baseEquipment.getName() + " 的信息：" + result);
        return ApiRestResponse.success(result);
    }
    /**
     * 新增数据
     *
     * @param baseEquipment 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody BaseEquipment baseEquipment, HttpServletRequest request) {
        QueryWrapper<BaseEquipment> baseEquipmentQueryWrapper = new QueryWrapper<>();
        baseEquipmentQueryWrapper.eq("name",baseEquipment.getName()).or().eq("serial_number",baseEquipment.getSerialNumber());
        BaseEquipment one = baseEquipmentService.getOne(baseEquipmentQueryWrapper);
        if(one != null)
            throw new DamPourException(DampouringExceptionEnum.NAME_EXISTED);
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        baseEquipment.setOperator(username);
        baseEquipment.setCreateTime(TimeUtils.getNowTime());

        BaseAcquisitionEqu baseAcquisitionEqu = baseAcquisitionEquService.getById(baseEquipment.getAcquId());
        if (baseAcquisitionEqu == null)
        {
            throw new DamPourException(30008,"该采集器不存在");
        }
        this.baseEquipmentService.save(baseEquipment);
        DeviceUtils deviceUtils = DeviceUtils.getDeviceUtil(baseAcquisitionEqu.getDk());
        if(deviceUtils.portIsOpen())
        {
            baseEquipment.setFirmwareVersion(deviceUtils.getVersion());
            baseEquipmentService.updateById(baseEquipment);
            List<BaseStationNode> baseStationNodes = deviceUtils.getNodesByDeviceNumber(baseEquipment.getSerialNumber());
            baseStationNodes.forEach(t->t.setStationId(baseEquipment.getId()));
            baseStationNodeService.saveBatch(baseStationNodes);
        }
        return ApiRestResponse.success();
    }
    @GetMapping("reGetNodes")
    public ApiRestResponse reGetNodes(BaseEquipment baseEquipment)
    {
        BaseAcquisitionEqu baseAcquisitionEqu = baseAcquisitionEquService.getById(baseEquipment.getAcquId());
        DeviceUtils deviceUtils = DeviceUtils.getDeviceUtil(baseAcquisitionEqu.getDk());
        QueryWrapper<BaseStation> baseStationQueryWrapper = new QueryWrapper<>();
        baseStationQueryWrapper.eq("monitor_equ_id",baseEquipment.getId());
        BaseStation one = baseStationService.getOne(baseStationQueryWrapper);
        if(deviceUtils.portIsOpen())
        {
            List<BaseStationNode> baseStationNodes = deviceUtils.getNodesByDeviceNumber(baseEquipment.getSerialNumber());

            baseStationNodes.forEach(t->t.setStationId(one.getId()));
            QueryWrapper<BaseStationNode> baseStationNodeQueryWrapper = new QueryWrapper<>();
            baseStationNodeQueryWrapper.eq("station_id",one.getId());
            if(baseStationNodeService.remove(baseStationNodeQueryWrapper))
            {
                baseStationNodeService.saveBatch(baseStationNodes);
            }
            return ApiRestResponse.success(baseStationNodes);
        }
        else
        {
            return ApiRestResponse.error(30000,"设备串口没有开");
        }
    }
    @GetMapping("reGetVersion")
    public ApiRestResponse reGetVersion(BaseEquipment baseEquipment)
    {
        BaseAcquisitionEqu baseAcquisitionEqu = baseAcquisitionEquService.getById(baseEquipment.getAcquId());
        DeviceUtils deviceUtils = DeviceUtils.getDeviceUtil(baseAcquisitionEqu.getDk());
        if(deviceUtils.portIsOpen())
        {
            String version = deviceUtils.getVersion();
            baseEquipment.setFirmwareVersion(version);
            baseEquipmentService.updateById(baseEquipment);
            return ApiRestResponse.success(version);
        }
        else
        {
            return ApiRestResponse.error(30000,"设备串口没有开");
        }
    }

    @GetMapping("reGetModel")
    public ApiRestResponse reGetModel(BaseEquipment baseEquipment)
    {
        BaseAcquisitionEqu baseAcquisitionEqu = baseAcquisitionEquService.getById(baseEquipment.getAcquId());
        DeviceUtils deviceUtils = DeviceUtils.getDeviceUtil(baseAcquisitionEqu.getDk());
        if(deviceUtils.portIsOpen())
        {
            List<String> model = deviceUtils.getModel(baseEquipment.getSerialNumber());
            return ApiRestResponse.success(model);
        }
        else
        {
            return ApiRestResponse.error(30000,"设备串口没有开");
        }
    }
    @GetMapping("reSetModel")
    public ApiRestResponse reSetModel(BaseEquipment baseEquipment,String model1,String model2)
    {
        BaseAcquisitionEqu baseAcquisitionEqu = baseAcquisitionEquService.getById(baseEquipment.getAcquId());
        DeviceUtils deviceUtils = DeviceUtils.getDeviceUtil(baseAcquisitionEqu.getDk());
        if(deviceUtils.portIsOpen())
        {
            if(deviceUtils.setModel(baseEquipment.getSerialNumber(),model1,model2) && deviceUtils.setSave())
                return ApiRestResponse.success();
            else return ApiRestResponse.error(30000,"更新失败");
        }
        else
        {
            return ApiRestResponse.error(30000,"设备串口没有开");
        }
    }
    @GetMapping("rearrangementNode")
    public ApiRestResponse rearrangementNode(BaseEquipment baseEquipment)
    {
        BaseAcquisitionEqu baseAcquisitionEqu = baseAcquisitionEquService.getById(baseEquipment.getAcquId());
        DeviceUtils deviceUtils = DeviceUtils.getDeviceUtil(baseAcquisitionEqu.getDk());
        if(deviceUtils.portIsOpen())
        {
            deviceUtils.reGetCall();
            deviceUtils.reset();
            return ApiRestResponse.success();
        }
        else
        {
            return ApiRestResponse.error(30000,"设备串口没有开");
        }
    }
    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<BaseEquipment>  baseEquipmentList, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        baseEquipmentList.forEach(t-> t.setOperator(username));
        baseEquipmentList.forEach(t-> t.setCreateTime(TimeUtils.getNowTime()));
        return ApiRestResponse.success(this.baseEquipmentService.saveBatch(baseEquipmentList));
    }


    /**
     * 修改数据
     *
     * @param baseEquipment 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody BaseEquipment baseEquipment) {
            return ApiRestResponse.success(this.baseEquipmentService.updateById(baseEquipment));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.baseEquipmentService.removeByIds(idList));
    }

    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.baseEquipmentService.list(),"BaseEquipment","BaseEquipment",BaseEquipment.class,"BaseEquipment",response);
    }

}

