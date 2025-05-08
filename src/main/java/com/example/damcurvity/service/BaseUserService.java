package com.example.damcurvity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.damcurvity.entity.BaseUser;

/**
 * (BaseUser)表服务接口
 *
 * @author makejava
 * @since 2023-08-05 15:08:19
 */
public interface BaseUserService extends IService<BaseUser> {

    BaseUser login(String userName, String pwd, String ip);

    void logout(String userName, String ip);
}

