package com.example.damcurvity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.damcurvity.entity.BaseStation;
import com.example.damcurvity.excelVO.ReportExcelVO;
import com.example.damcurvity.vo.BaseStationVO;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * (BaseStation)表服务接口
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
public interface BaseStationService extends IService<BaseStation> {
    public void addBaseStation(BaseStation baseStation);


    Page<BaseStationVO> selectListJoin(Page<BaseStationVO> page, @Param("baseStation") BaseStation baseStation);

    List<ReportExcelVO> exportReport(Integer jg, Integer dataType, String startTime, String endTime);

    Workbook getReportSheet(String startTime, String endTime, Integer interval, Integer stationId);
}

