package com.example.damcurvity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.damcurvity.entity.StationInitValue;

import java.util.Date;

/**
 * (StationInitValue)表服务接口
 *
 * @author makejava
 * @since 2023-08-31 22:37:40
 */
public interface StationInitValueService extends IService<StationInitValue> {

    void addByStationIdAndTime(Integer stationId, String time);

    Object addStationInitValue(StationInitValue stationInitValue);
}

