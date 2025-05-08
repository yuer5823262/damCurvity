package com.example.damcurvity.controller;


import com.example.damcurvity.common.ApiRestResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.common.Constant;
import com.example.damcurvity.entity.BaseStation;
import com.example.damcurvity.excelVO.ReportExcelVO;
import com.example.damcurvity.vo.BaseStationVO;
import io.swagger.annotations.Api;
import com.example.damcurvity.service.BaseStationService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import com.example.damcurvity.que.BaseQue;
import com.example.damcurvity.util.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * (BaseStation)表控制层
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Api("baseStation")
@RestController
@RequestMapping("baseStation")
public class BaseStationController {
    /**
     * 服务对象
     */
    @Resource
    private BaseStationService baseStationService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param baseStation 查询实体
     * @return 所有数据
     */
    @GetMapping("select")
    public ApiRestResponse selectAll(BaseQue baseQue, BaseStation baseStation) {
            Page<BaseStation> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
            return ApiRestResponse.success(this.baseStationService.page(page, new QueryWrapper<>(baseStation)));
    }
    @ApiOperation("获取测点信息，设备拓扑")
    @GetMapping("selectList")
    public ApiRestResponse selectList(BaseQue baseQue, BaseStation baseStation) {
        Page<BaseStationVO> page = new Page<>(baseQue.getPageNum(),baseQue.getPageSize());
        return ApiRestResponse.success(this.baseStationService.selectListJoin(page, baseStation));
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("getOneDdata")
    public ApiRestResponse selectOne(Integer id) {
            return ApiRestResponse.success(this.baseStationService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param baseStation 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public ApiRestResponse insert(@RequestBody BaseStation baseStation) {
        baseStationService.addBaseStation(baseStation);
        return ApiRestResponse.success();
    }


    @PostMapping("addBatch")
    public ApiRestResponse insertBatch(@RequestBody List<BaseStation>  baseStationList) {
        return ApiRestResponse.success(this.baseStationService.saveBatch(baseStationList));
    }

    @GetMapping("exportReport")
    @ApiOperation("导出报表")
    public void exportReport(HttpServletResponse response, Integer jg, Integer dataType, String startTime, String endTime) {

        ExcelUtil.exportExcel(this.baseStationService.exportReport(jg, dataType, startTime, endTime),"Report","BaseStation", ReportExcelVO.class,"Report.xls",response);

    }


    @GetMapping("exportReportNew")
    @ApiOperation("导出报表new")
    public void exportReportNew(HttpServletResponse response, Integer jg, Integer stationId, String startTime, String endTime) {
        Workbook workbook= this.baseStationService.getReportSheet(startTime,endTime,jg,stationId);
        ExcelUtil.downLoadExcel("report.xls",response,workbook);

    }
    /**
     * 修改数据
     *
     * @param baseStation 实体对象
     * @return 修改结果
     */
    @PostMapping("update")
    public ApiRestResponse update(@RequestBody BaseStation baseStation) {
            return ApiRestResponse.success(this.baseStationService.updateById(baseStation));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @GetMapping("deleteByIds")
    public ApiRestResponse delete(@RequestParam("idList") List<Long> idList) {
            return ApiRestResponse.success(this.baseStationService.removeByIds(idList));
    }
    @ApiOperation(value = "导出Excel")
    @GetMapping("/exportList")
    public void exportMemberList(HttpServletResponse response) {
        ExcelUtil.exportExcel(this.baseStationService.list(),"BaseStation","BaseStation",BaseStation.class,"BaseStation",response);
    }
}

