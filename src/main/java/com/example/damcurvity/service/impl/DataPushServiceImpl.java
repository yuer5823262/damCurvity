package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.DataPushDao;
import com.example.damcurvity.entity.DataPush;
import com.example.damcurvity.service.DataPushService;
import org.springframework.stereotype.Service;

/**
 * (DataPush)表服务实现类
 *
 * @author makejava
 * @since 2023-08-27 09:19:04
 */
@Service("dataPushService")
public class DataPushServiceImpl extends ServiceImpl<DataPushDao, DataPush> implements DataPushService {

}

