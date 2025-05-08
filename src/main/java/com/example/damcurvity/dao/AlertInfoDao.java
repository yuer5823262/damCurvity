package com.example.damcurvity.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.que.InfoAlertQue;
import com.example.damcurvity.vo.EquStateVO;
import com.example.damcurvity.vo.NodeStateVO;
import com.example.damcurvity.vo.TodayAlertCountVO;
import com.example.damcurvity.vo.TodayAlertInfoVO;
import org.apache.ibatis.annotations.Param;
import com.example.damcurvity.entity.AlertInfo;
import org.apache.ibatis.annotations.Select;

/**
 * (AlertInfo)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
public interface AlertInfoDao extends BaseMapper<AlertInfo> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<AlertInfo> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<AlertInfo> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<AlertInfo> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<AlertInfo> entities);

    Object selectListJoin(Page<AlertInfo> page, @Param("infoAlertQue")InfoAlertQue infoAlertQue);


    @Select("SELECT `level`,count(*) as count from alert_info\n" +
            "WHERE time > CURRENT_DATE()\n" +
            "GROUP BY `level`")
    List<TodayAlertInfoVO> todayAlertInfo();




    @Select("SELECT \n" +
            "    (SELECT COUNT(*) FROM base_station) AS DeviceAlertCount,\n" +
            "    (SELECT COUNT(DISTINCT position) FROM alert_info WHERE time > CURDATE()) AS StationCount;")
    TodayAlertCountVO todayAlertCount();



    @Select("SELECT bs.name as stationName,tb1.voltage < be.vol_threshold as state from base_station bs\n" +
            "LEFT JOIN base_equipment be on bs.monitor_equ_id=be.id\n" +
            "LEFT JOIN (SELECT station_id,avg(voltage) as voltage from info_station_node WHERE time >(NOW() - INTERVAL 2 HOUR) GROUP BY station_id) tb1 on tb1.station_id=bs.id")
    List<EquStateVO> checkEuqState();


    @Select("SELECT tb1.name as nodeName,tb2.xy_type as xyType,tb2.count as count from (SELECT name from base_station_node WHERE station_id = #{stationId}) tb1\n" +
            "LEFT JOIN (SELECT node,xy_type,count(*) as count FROM alert_info WHERE time > CURDATE() and content like CONCAT('%', #{type}, '%') GROUP BY node,xy_type) tb2 on tb2.node = tb1.`name`")
    List<NodeStateVO> checkNodeState(@Param("stationId") Integer stationId,@Param("type") String type);
}

