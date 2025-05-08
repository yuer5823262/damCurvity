package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.UserLogDao;
import com.example.damcurvity.entity.UserLog;
import com.example.damcurvity.service.UserLogService;
import org.springframework.stereotype.Service;

/**
 * (UserLog)表服务实现类
 *
 * @author makejava
 * @since 2023-08-05 16:31:59
 */
@Service("userLogService")
public class UserLogServiceImpl extends ServiceImpl<UserLogDao, UserLog> implements UserLogService {

}

