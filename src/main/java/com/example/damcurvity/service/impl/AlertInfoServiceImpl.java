package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.AlertInfoDao;
import com.example.damcurvity.entity.AlertInfo;
import com.example.damcurvity.que.InfoAlertQue;
import com.example.damcurvity.service.AlertInfoService;
import org.springframework.stereotype.Service;

/**
 * (AlertInfo)表服务实现类
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Service("alertInfoService")
public class AlertInfoServiceImpl extends ServiceImpl<AlertInfoDao, AlertInfo> implements AlertInfoService {

    @Override
    public Object selectListJoin(Page<AlertInfo> page, InfoAlertQue infoAlertQue) {
        return this.getBaseMapper().selectListJoin(page,infoAlertQue);
    }
}

