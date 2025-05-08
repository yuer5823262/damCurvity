package com.example.damcurvity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.damcurvity.entity.InfoStaionNodeXz;
import com.example.damcurvity.entity.InfoStationNode;
import com.example.damcurvity.que.InfoStationNodeQue;
import com.example.damcurvity.vo.InfoStationNodeVO;

import java.util.List;

/**
 * (InfoStaionNodeXz)表服务接口
 *
 * @author makejava
 * @since 2023-10-09 20:56:18
 */
public interface InfoStaionNodeXzService extends IService<InfoStaionNodeXz> {

    Object selectListJoin(Page<InfoStationNodeVO> page, InfoStationNodeQue infoStationNodeQue);

    Page<InfoStationNodeVO> selectByInterval(Page<InfoStationNodeVO> page, InfoStationNodeQue infoStationNodeQue);
}

