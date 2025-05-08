package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.BaseStationNodeDao;
import com.example.damcurvity.entity.BaseStationNode;
import com.example.damcurvity.service.BaseStationNodeService;
import org.springframework.stereotype.Service;

/**
 * (BaseStationNode)表服务实现类
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Service("baseStationNodeService")
public class BaseStationNodeServiceImpl extends ServiceImpl<BaseStationNodeDao, BaseStationNode> implements BaseStationNodeService {

}

