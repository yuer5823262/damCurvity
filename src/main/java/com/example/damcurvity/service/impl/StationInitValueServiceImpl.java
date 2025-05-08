package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.InfoStationNodeDao;
import com.example.damcurvity.dao.StationInitValueDao;
import com.example.damcurvity.entity.InfoStationNode;
import com.example.damcurvity.entity.StationInitValue;
import com.example.damcurvity.service.StationInitValueService;
import com.example.damcurvity.util.copyUtils;
import lombok.var;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (StationInitValue)表服务实现类
 *
 * @author makejava
 * @since 2023-08-31 22:37:40
 */
@Service("stationInitValueService")
public class StationInitValueServiceImpl extends ServiceImpl<StationInitValueDao, StationInitValue> implements StationInitValueService {
    @Resource
    InfoStationNodeDao infoStationNodeDao;
    @Override
    public void addByStationIdAndTime(Integer stationId, String time) {
        QueryWrapper<InfoStationNode> infoStationNodeQueryWrapper = new QueryWrapper<>();
        infoStationNodeQueryWrapper.eq("time",time);
        infoStationNodeQueryWrapper.eq("station_id",stationId);
        List<InfoStationNode> infoStationNodeList = infoStationNodeDao.selectList(infoStationNodeQueryWrapper);
        for (InfoStationNode infoStationNode : infoStationNodeList) {
            QueryWrapper<StationInitValue> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("node_id",infoStationNode.getNodeId());
            StationInitValue stationInitValue = this.getOne(queryWrapper);
            infoStationNode.setId(null);
            if (stationInitValue == null) {
                stationInitValue = new StationInitValue();
                copyUtils.copyPropertiesIgnoreNull(infoStationNode,stationInitValue);

                this.save(stationInitValue);
            }
            else {
                copyUtils.copyPropertiesIgnoreNull(infoStationNode,stationInitValue);
                this.updateById(stationInitValue);
            }

        }

    }

    @Override
    public Object addStationInitValue(StationInitValue stationInitValue) {
        QueryWrapper<StationInitValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("node_id",stationInitValue.getNodeId());
        StationInitValue temp = this.getOne(queryWrapper);

        if(temp!=null)
        {

            stationInitValue.setId(temp.getId());
            this.updateById(stationInitValue);
        }
        else
        {
            stationInitValue.setId(null);
            this.save(stationInitValue);
        }
        return null;
    }
}

