package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.InfoStaionNodeXzDao;
import com.example.damcurvity.entity.InfoStaionNodeXz;
import com.example.damcurvity.entity.InfoStationNode;
import com.example.damcurvity.que.InfoStationNodeQue;
import com.example.damcurvity.service.InfoStaionNodeXzService;
import com.example.damcurvity.vo.InfoStationNodeVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (InfoStaionNodeXz)表服务实现类
 *
 * @author makejava
 * @since 2023-10-09 20:56:18
 */
@Service("infoStaionNodeXzService")
public class InfoStaionNodeXzServiceImpl extends ServiceImpl<InfoStaionNodeXzDao, InfoStaionNodeXz> implements InfoStaionNodeXzService {

    @Override
    public Page<InfoStationNodeVO> selectListJoin(Page<InfoStationNodeVO> page, InfoStationNodeQue infoStationNode) {

        return this.getBaseMapper().selectListJoin(page,infoStationNode);
    }

    @Override
    public Page<InfoStationNodeVO> selectByInterval(Page<InfoStationNodeVO> page, InfoStationNodeQue infoStationNodeQue) {
        return this.getBaseMapper().selectByInterval(page,infoStationNodeQue);
    }
}

