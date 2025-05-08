package com.example.damcurvity.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.entity.BaseStation;
import com.example.damcurvity.entity.InfoStationNode;
import com.example.damcurvity.excelVO.StationDataTemp;
import com.example.damcurvity.exception.DamPourException;
import com.example.damcurvity.exception.DampouringExceptionEnum;
import com.example.damcurvity.que.InfoStationNodeQue;
import com.example.damcurvity.service.BaseStationService;
import com.example.damcurvity.util.copyUtils;
import com.example.damcurvity.vo.InfoStationNodeVO;
import io.swagger.annotations.Api;
import com.example.damcurvity.service.InfoStationNodeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import com.example.damcurvity.que.BaseQue;
import com.example.damcurvity.util.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (InfoStationNode)表控制层
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Api("infoStationNode")
@RestController
@RequestMapping("infoStationNode")
public class InfoStationNodeController {
    /**
     * 服务对象
     */
    @Resource
    private InfoStationNodeService infoStationNodeService;
    @Resource
    private BaseStationService baseStationService;
    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param infoStationNode 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(InfoStationNodeQue infoStationNodeQue) {
            Page<InfoStationNodeVO> page = new Page<>(infoStationNodeQue.getPageNum(),infoStationNodeQue.getPageSize());

            return ApiRestResponse.success(this.infoStationNodeService.selectListJoin(page, infoStationNodeQue));
    }
    @GetMapping ("selectByInterval")
    ApiRestResponse selectByInterval(InfoStationNodeQue infoStationNodeQue)
    {
        infoStationNodeQue.setDisplayNumByInterval();
        Page<InfoStationNodeVO> page = new Page<>(infoStationNodeQue.getPageNum(),infoStationNodeQue.getPageSize());
        Page<InfoStationNodeVO> infoStationNodeList = infoStationNodeService.selectByInterval(page,infoStationNodeQue);

        return ApiRestResponse.success(infoStationNodeList);
    }





    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
            return ApiRestResponse.success(this.infoStationNodeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param infoStationNode 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody InfoStationNode infoStationNode) {
            return ApiRestResponse.success(this.infoStationNodeService.save(infoStationNode));
    }


    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<InfoStationNode>  infoStationNodeList) {
        return ApiRestResponse.success(this.infoStationNodeService.saveBatch(infoStationNodeList));
    }


    /**
     * 修改数据
     *
     * @param infoStationNode 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody InfoStationNode infoStationNode) {
            return ApiRestResponse.success(this.infoStationNodeService.updateById(infoStationNode));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.infoStationNodeService.removeByIds(idList));
    }
    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.infoStationNodeService.list(),"InfoStationNode","InfoStationNode",InfoStationNode.class,"InfoStationNode",response);
    }

    @ApiOperation(value = "导入模板")
    @GetMapping("/muban")
    public void muban(HttpServletResponse response) {
        List<StationDataTemp> memberList = new ArrayList<>();
        ExcelUtil.exportExcel(memberList,"数据导入模板","数据导入模板",StationDataTemp.class,"muban.csv",response);
    }




    @ApiOperation("从Excel导入")
    @PostMapping("/importMemberList")
    @ResponseBody
    public ApiRestResponse importMemberList(@RequestPart("file") MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        try {
            List<StationDataTemp> list = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    StationDataTemp.class, params);
            List<InfoStationNode> infoStationNodeList = new ArrayList<>();
            for (StationDataTemp stationDataTemp:list)
            {
                InfoStationNode infoStationNode = new InfoStationNode();
                QueryWrapper<BaseStation> baseStationQueryWrapper = new QueryWrapper<>();
                baseStationQueryWrapper.eq("name",stationDataTemp.getStationName());
                BaseStation baseStation = baseStationService.getOne(baseStationQueryWrapper);
                copyUtils.copyPropertiesIgnoreNull(stationDataTemp,infoStationNode);
                infoStationNode.setStationId(baseStation.getId());
                infoStationNodeList.add(infoStationNode);
            }
            infoStationNodeService.saveBatch(infoStationNodeList);
            return ApiRestResponse.success(list);
        } catch (Exception e) {
            throw new DamPourException(DampouringExceptionEnum.DAORU_WRONG);
        }
    }
}

