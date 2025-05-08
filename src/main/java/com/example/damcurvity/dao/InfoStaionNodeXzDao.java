package com.example.damcurvity.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.damcurvity.entity.InfoStationNode;
import com.example.damcurvity.que.InfoStationNodeQue;
import com.example.damcurvity.vo.InfoStationNodeVO;
import org.apache.ibatis.annotations.Param;
import com.example.damcurvity.entity.InfoStaionNodeXz;

/**
 * (InfoStaionNodeXz)表数据库访问层
 *
 * @author makejava
 * @since 2023-10-09 20:56:18
 */
public interface InfoStaionNodeXzDao extends BaseMapper<InfoStaionNodeXz> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<InfoStaionNodeXz> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<InfoStaionNodeXz> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<InfoStaionNodeXz> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<InfoStaionNodeXz> entities);

    Page<InfoStationNodeVO> selectListJoin(Page<InfoStationNodeVO> page, @Param("infoStationNodeQue")InfoStationNodeQue infoStationNode);

    Page<InfoStationNodeVO> selectByInterval(Page<InfoStationNodeVO> page, @Param("infoStationNodeQue")InfoStationNodeQue infoStationNodeQue);
}

