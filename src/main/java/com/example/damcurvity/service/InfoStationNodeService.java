package com.example.damcurvity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.damcurvity.entity.InfoStaionNodeXz;
import com.example.damcurvity.entity.InfoStationNode;
import com.example.damcurvity.que.InfoStationNodeQue;
import com.example.damcurvity.que.InfoStationNodeXzQue;
import com.example.damcurvity.vo.InfoStationNodeChangeRateVO;
import com.example.damcurvity.vo.InfoStationNodeVO;

import java.util.List;

/**
 * (InfoStationNode)表服务接口
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
public interface InfoStationNodeService extends IService<InfoStationNode> {

    Page<InfoStationNodeVO> selectListJoin(Page<InfoStationNodeVO> page, InfoStationNodeQue infoStationNode);

    Page<InfoStationNodeVO> selectByInterval(Page<InfoStationNodeVO> page, InfoStationNodeQue infoStationNodeQue);

    List<InfoStaionNodeXz> selectByIntervalXz(InfoStationNodeXzQue infoStationNodeXzQue);

    List<InfoStationNodeChangeRateVO> change_rage();
}

