package com.example.damcurvity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.damcurvity.entity.AlertInfo;
import com.example.damcurvity.que.InfoAlertQue;

/**
 * (AlertInfo)表服务接口
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
public interface AlertInfoService extends IService<AlertInfo> {

    Object selectListJoin(Page<AlertInfo> page, InfoAlertQue infoAlertQue);
}

