package com.example.damcurvity.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.example.damcurvity.entity.StationInitValue;

/**
 * (StationInitValue)表数据库访问层
 *
 * @author makejava
 * @since 2023-08-31 22:37:40
 */
public interface StationInitValueDao extends BaseMapper<StationInitValue> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<StationInitValue> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<StationInitValue> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<StationInitValue> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<StationInitValue> entities);

}

