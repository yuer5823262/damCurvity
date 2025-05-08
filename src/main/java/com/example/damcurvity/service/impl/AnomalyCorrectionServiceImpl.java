package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.AnomalyCorrectionDao;
import com.example.damcurvity.dao.InfoStationNodeDao;
import com.example.damcurvity.entity.AnomalyCorrection;
import com.example.damcurvity.entity.InfoStationNode;
import com.example.damcurvity.que.InfoStationNodeQue;
import com.example.damcurvity.service.AnomalyCorrectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AnomalyCorrection)表服务实现类
 *
 * @author makejava
 * @since 2023-09-03 18:58:34
 */
@Service("anomalyCorrectionService")
public class AnomalyCorrectionServiceImpl extends ServiceImpl<AnomalyCorrectionDao, AnomalyCorrection> implements AnomalyCorrectionService {
    @Resource
    InfoStationNodeDao infoStationNodeDao;
    @Override
    public Boolean addAnomalyCorrection(AnomalyCorrection anomalyCorrection) {
        QueryWrapper<InfoStationNode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("node_id",anomalyCorrection.getNodeId());
        queryWrapper.gt("time",anomalyCorrection.getStartTime()).lt("time",anomalyCorrection.getEndTime());
        List<InfoStationNode> infoStationNodeList = infoStationNodeDao.selectList(queryWrapper);
        if (infoStationNodeList.size()==0)
            return false;
        InfoStationNode startInfoStationNode = infoStationNodeList.get(0);
        InfoStationNode endInfoStationNode = infoStationNodeList.get(infoStationNodeList.size()-1);
        if(startInfoStationNode !=null && endInfoStationNode !=null)
        {
            anomalyCorrection.setAllVal(startInfoStationNode,endInfoStationNode);
            anomalyCorrection.setId(null);
            return this.save(anomalyCorrection);
        }
        else return true;

    }
}

