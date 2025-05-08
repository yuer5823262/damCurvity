package com.example.damcurvity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.damcurvity.entity.BaseEquipment;
import com.example.damcurvity.vo.BaseEquipmentVO;
import com.example.damcurvity.vo.BaseStationVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (BaseEquipment)表服务接口
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
public interface BaseEquipmentService extends IService<BaseEquipment> {

    Page<BaseEquipmentVO> selectListJoin(Page<BaseEquipment> page, @Param("baseEquipment") BaseEquipment baseEquipment);
}

