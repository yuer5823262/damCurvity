package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.entity.AnomalyCorrection;
import com.example.damcurvity.req.AddAnomalyCorrectionsReq;
import io.swagger.annotations.Api;
import com.example.damcurvity.util.JwtUtils;
import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.service.AnomalyCorrectionService;
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
 * (AnomalyCorrection)表控制层
 *
 * @author makejava
 * @since 2023-09-03 18:58:34
 */
@Api("anomalyCorrection")
@RestController
@RequestMapping("anomalyCorrection")
public class AnomalyCorrectionController {
    /**
     * 服务对象
     */
    @Resource
    private AnomalyCorrectionService anomalyCorrectionService;

    /**
     * 分页查询所有数据
     *
     * @param page              分页对象
     * @param anomalyCorrection 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(BaseQue baseQue, AnomalyCorrection anomalyCorrection) {
        Page<AnomalyCorrection> page = new Page<>(baseQue.getPageNum(), baseQue.getPageSize());
        return ApiRestResponse.success(this.anomalyCorrectionService.page(page, new QueryWrapper<>(anomalyCorrection)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
        return ApiRestResponse.success(this.anomalyCorrectionService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param anomalyCorrection 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody AnomalyCorrection anomalyCorrection, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = JwtUtils.GetUserName(token);
        anomalyCorrection.setOperator(username);
        anomalyCorrection.setCreateTime(TimeUtils.getNowTime());
        return ApiRestResponse.success(this.anomalyCorrectionService.addAnomalyCorrection(anomalyCorrection));
    }
    @PostMapping("addNodes")
    public ApiRestResponse addNodes(@RequestBody AddAnomalyCorrectionsReq addAnomalyCorrectionsReq, HttpServletRequest request) {
        AnomalyCorrection anomalyCorrection = new AnomalyCorrection();
        String token = request.getHeader("token");
        String username = JwtUtils.GetUserName(token);
        anomalyCorrection.setOperator(username);
        anomalyCorrection.setCreateTime(TimeUtils.getNowTime());
        anomalyCorrection.setStartTime(addAnomalyCorrectionsReq.getStartTime());
        anomalyCorrection.setEndTime(addAnomalyCorrectionsReq.getEndTime());
        anomalyCorrection.setStationId(addAnomalyCorrectionsReq.getStationId());
        for (Integer node : addAnomalyCorrectionsReq.getNodeList()) {
            anomalyCorrection.setNodeId(node);
            this.anomalyCorrectionService.addAnomalyCorrection(anomalyCorrection);
        }

        return ApiRestResponse.success();
    }

    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<AnomalyCorrection> anomalyCorrectionList, HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = JwtUtils.GetUserName(token);
        anomalyCorrectionList.forEach(t -> t.setOperator(username));
        anomalyCorrectionList.forEach(t -> t.setCreateTime(TimeUtils.getNowTime()));
        return ApiRestResponse.success(this.anomalyCorrectionService.saveBatch(anomalyCorrectionList));
    }


    /**
     * 修改数据
     *
     * @param anomalyCorrection 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody AnomalyCorrection anomalyCorrection) {
        return ApiRestResponse.success(this.anomalyCorrectionService.updateById(anomalyCorrection));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
        return ApiRestResponse.success(this.anomalyCorrectionService.removeByIds(idList));
    }


    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.anomalyCorrectionService.list(), "AnomalyCorrection", "AnomalyCorrection", AnomalyCorrection.class, "AnomalyCorrection", response);
    }

}

