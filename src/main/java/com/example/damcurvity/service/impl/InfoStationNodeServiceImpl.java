package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.InfoStationNodeDao;
import com.example.damcurvity.entity.InfoStaionNodeXz;
import com.example.damcurvity.entity.InfoStationNode;
import com.example.damcurvity.que.InfoStationNodeQue;
import com.example.damcurvity.que.InfoStationNodeXzQue;
import com.example.damcurvity.service.InfoStationNodeService;
import com.example.damcurvity.vo.InfoStationNodeChangeRateVO;
import com.example.damcurvity.vo.InfoStationNodeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (InfoStationNode)表服务实现类
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Service("infoStationNodeService")
public class InfoStationNodeServiceImpl extends ServiceImpl<InfoStationNodeDao, InfoStationNode> implements InfoStationNodeService {

    @Override
    public Page<InfoStationNodeVO> selectListJoin(Page<InfoStationNodeVO> page, InfoStationNodeQue infoStationNode) {

        return this.getBaseMapper().selectListJoin(page,infoStationNode);
    }

    @Override
    public Page<InfoStationNodeVO> selectByInterval(Page<InfoStationNodeVO> page,  InfoStationNodeQue infoStationNodeQue) {
        return this.getBaseMapper().selectByInterval(page,infoStationNodeQue);
    }



    @Override
    public List<InfoStaionNodeXz> selectByIntervalXz(InfoStationNodeXzQue infoStationNodeXzQue) {
        return this.getBaseMapper().selectByIntervalXz(infoStationNodeXzQue);
    }

    @Override
    public List<InfoStationNodeChangeRateVO> change_rage() {
        return this.getBaseMapper().change_rage();
    }
}

