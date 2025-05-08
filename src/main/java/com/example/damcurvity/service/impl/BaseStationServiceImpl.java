package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.StaticSchedule.DataGetScheduleTask;
import com.example.damcurvity.common.Constant;
import com.example.damcurvity.dao.BaseStationDao;
import com.example.damcurvity.entity.BaseStation;
import com.example.damcurvity.excelVO.ReportExcelVO;
import com.example.damcurvity.service.BaseStationService;
import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.vo.BaseStationVO;
import com.example.damcurvity.vo.ReportSheet;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * (BaseStation)表服务实现类
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Service("baseStationService")
public class BaseStationServiceImpl extends ServiceImpl<BaseStationDao, BaseStation> implements BaseStationService {
    @Resource
    DataGetScheduleTask dataGetScheduleTask;
    @Resource
    BaseStationDao baseStationDao;
    @Override
    public void addBaseStation(BaseStation baseStation) {

        this.save(baseStation);
        dataGetScheduleTask.addTask(baseStation);
    }

    @Override
    public Page<BaseStationVO> selectListJoin(Page<BaseStationVO> page, BaseStation baseStation) {
        return baseStationDao.selectListJoin(page,baseStation);
    }

    @Override
    public List<ReportExcelVO> exportReport(Integer jg, Integer dataType, String startTime, String endTime) {
        return baseStationDao.exportReport(jg, dataType, startTime, endTime);
    }

    @Override
    public Workbook getReportSheet(String startTime, String endTime, Integer interval, Integer stationId)
    {
        BaseStation baseStation = baseStationDao.selectById(stationId);
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\static\\temp.xlsx");
            Workbook workbook = WorkbookFactory.create(file);
            String tempTime = endTime;
            int count = 0;
            for(int j=0;j<30;j++) {
                if (tempTime.compareTo(startTime)<=0)
                    break;
                List<ReportSheet> reportSheets = baseStationDao.getReportSheet(tempTime, interval, stationId);

                tempTime = TimeUtils.byTimeNextDay(tempTime,-interval);

                if(reportSheets.size() ==0) continue;
                workbook.setSheetName(count, tempTime.substring(0,10).replace(" ","-"));
                Sheet sheet = workbook.getSheetAt(count);
                if(interval == 1) sheet.getRow(0).getCell(0).setCellValue("深层水平位移监测日报表");
                if(interval == 7) sheet.getRow(0).getCell(0).setCellValue("深层水平位移监测周报表");
                if(interval == 30) sheet.getRow(0).getCell(0).setCellValue("深层水平位移监测月报表");
                sheet.getRow(1).getCell(5).setCellValue("报表编号：R-HS-"+TimeUtils.getNowTime().substring(0,13));
                sheet.getRow(2).getCell(3).setCellValue("本次监测时间:"+TimeUtils.getNowTime());
                sheet.getRow(2).getCell(0).setCellValue("上次监测时间:"+"*");
                sheet.getRow(3).getCell(3).setCellValue("仪器出厂编号："+baseStation.getName());
                sheet.getRow(4).getCell(0).setCellValue("测点："+baseStation.getName());
                int rowIndexToInsert = 6;
                sheet.shiftRows(rowIndexToInsert, sheet.getLastRowNum(), reportSheets.size());
                double maxChange = 0.;
                double maxLjChange = 0.;
                for (int i = 0; i < reportSheets.size(); i++) {
                    Row newRow = sheet.createRow(rowIndexToInsert + i);
                    try {
                        newRow.createCell(0).setCellValue(reportSheets.get(i).getNodeId() + " /" + reportSheets.get(i).getGc());
                        newRow.createCell(1).setCellValue(reportSheets.get(i).getXChange());
                        newRow.createCell(2).setCellValue(reportSheets.get(i).getXChangeAll());
                        newRow.createCell(3).setCellValue(reportSheets.get(i).getXChangeRate());
                        newRow.createCell(4).setCellValue(reportSheets.get(i).getYChange());
                        newRow.createCell(5).setCellValue(reportSheets.get(i).getYChangeAll());
                        newRow.createCell(6).setCellValue(reportSheets.get(i).getYChangeRate());
                        if(reportSheets.get(i).getXChange() !=null && Math.abs(reportSheets.get(i).getXChange()) > maxChange) maxChange = reportSheets.get(i).getXChange();
                        if(reportSheets.get(i).getXChangeAll() !=null && Math.abs(reportSheets.get(i).getXChangeAll()) > maxLjChange) maxLjChange = reportSheets.get(i).getXChangeAll();
                    } catch (Exception e) {
                        Constant.logger.error("报表导出错误:",e);
                    }


                }
                sheet.getRow(7+reportSheets.size()).getCell(0).setCellValue(String.format("X坐标，最大本次变化：%fmm，最大累计变化：%fmm",maxChange,maxLjChange));
                count++;
            }
//            workbook.close();
            file.close();
            return workbook;
        } catch (IOException e) {
            Constant.logger.error("报表导出错误:",e);
        }

        return null;
    }

}

