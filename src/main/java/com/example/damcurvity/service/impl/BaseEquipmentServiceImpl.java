package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.BaseEquipmentDao;
import com.example.damcurvity.entity.BaseEquipment;
import com.example.damcurvity.service.BaseEquipmentService;
import com.example.damcurvity.vo.BaseEquipmentVO;
import com.example.damcurvity.vo.BaseStationVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (BaseEquipment)表服务实现类
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Service("baseEquipmentService")
public class BaseEquipmentServiceImpl extends ServiceImpl<BaseEquipmentDao, BaseEquipment> implements BaseEquipmentService {

    @Override
    public Page<BaseEquipmentVO> selectListJoin(Page<BaseEquipment> page, BaseEquipment baseEquipment) {
        return this.getBaseMapper().selectListJoin(page,baseEquipment);
    }
}

