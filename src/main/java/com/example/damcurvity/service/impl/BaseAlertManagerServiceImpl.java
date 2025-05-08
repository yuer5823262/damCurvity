package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.BaseAlertManagerDao;
import com.example.damcurvity.entity.BaseAlertManager;
import com.example.damcurvity.service.BaseAlertManagerService;
import org.springframework.stereotype.Service;

/**
 * (BaseAlertManager)表服务实现类
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Service("baseAlertManagerService")
public class BaseAlertManagerServiceImpl extends ServiceImpl<BaseAlertManagerDao, BaseAlertManager> implements BaseAlertManagerService {

}

