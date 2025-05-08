package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.entity.InfoStaionNodeXz;
import com.example.damcurvity.entity.InfoStationNode;
import com.example.damcurvity.que.InfoStationNodeQue;
import com.example.damcurvity.vo.InfoStationNodeVO;
import io.swagger.annotations.Api;
import com.example.damcurvity.util.JwtUtils;
import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.service.InfoStaionNodeXzService;
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
 * (InfoStaionNodeXz)表控制层
 *
 * @author makejava
 * @since 2023-10-09 20:56:18
 */
@Api("修正的数据")
@RestController
@RequestMapping("infoStaionNodeXz")
public class InfoStaionNodeXzController {
    /**
     * 服务对象
     */
    @Resource
    private InfoStaionNodeXzService infoStaionNodeXzService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param infoStaionNodeXz 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(InfoStationNodeQue infoStationNodeQue) {
        Page<InfoStationNodeVO> page = new Page<>(infoStationNodeQue.getPageNum(),infoStationNodeQue.getPageSize());
        return ApiRestResponse.success(this.infoStaionNodeXzService.selectListJoin(page, infoStationNodeQue));
    }
    @GetMapping ("selectByInterval")
    ApiRestResponse selectByInterval(BaseQue baseQue, InfoStationNodeQue infoStationNodeQue)
    {
        infoStationNodeQue.setDisplayNumByInterval();
        Page<InfoStationNodeVO> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
        Page<InfoStationNodeVO> infoStationNodeList = infoStaionNodeXzService.selectByInterval(page,infoStationNodeQue);

        return ApiRestResponse.success(infoStationNodeList);
    }

    /**
     * 新增数据
     *
     * @param infoStaionNodeXz 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody InfoStaionNodeXz infoStaionNodeXz, HttpServletRequest request) {
           String token = request.getHeader("token");
           String username =  JwtUtils.GetUserName(token);
           return ApiRestResponse.success(this.infoStaionNodeXzService.save(infoStaionNodeXz));
    }


    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<InfoStaionNodeXz>  infoStaionNodeXzList, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username =  JwtUtils.GetUserName(token);
        return ApiRestResponse.success(this.infoStaionNodeXzService.saveBatch(infoStaionNodeXzList));
    }


    /**
     * 修改数据
     *
     * @param infoStaionNodeXz 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody InfoStaionNodeXz infoStaionNodeXz) {
            return ApiRestResponse.success(this.infoStaionNodeXzService.updateById(infoStaionNodeXz));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.infoStaionNodeXzService.removeByIds(idList));
    }










    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.infoStaionNodeXzService.list(),"InfoStaionNodeXz","InfoStaionNodeXz",InfoStaionNodeXz.class,"InfoStaionNodeXz",response);
    }

}

