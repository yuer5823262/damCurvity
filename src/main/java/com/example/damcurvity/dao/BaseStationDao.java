package com.example.damcurvity.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.excelVO.ReportExcelVO;
import com.example.damcurvity.vo.BaseStationVO;
import com.example.damcurvity.vo.ReportSheet;
import org.apache.ibatis.annotations.Param;
import com.example.damcurvity.entity.BaseStation;

/**
 * (BaseStation)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
public interface BaseStationDao extends BaseMapper<BaseStation> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<BaseStation> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<BaseStation> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<BaseStation> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<BaseStation> entities);

    Page<BaseStationVO> selectListJoin(Page<BaseStationVO> page, @Param("baseStation") BaseStation baseStation);

    List<ReportExcelVO> exportReport(Integer jg, Integer dataType, String startTime, String endTime);

    List<ReportSheet> getReportSheet(String startTime, Integer interval, Integer stationId);
}

