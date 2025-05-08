package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.common.Constant;
import com.example.damcurvity.dao.BaseUserDao;
import com.example.damcurvity.dao.UserLogDao;
import com.example.damcurvity.entity.BaseUser;
import com.example.damcurvity.entity.UserLog;
import com.example.damcurvity.exception.DamPourException;
import com.example.damcurvity.exception.DampouringExceptionEnum;
import com.example.damcurvity.service.BaseUserService;
import com.example.damcurvity.util.MD5Utils;
import com.example.damcurvity.util.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * (BaseUser)表服务实现类
 *
 * @author makejava
 * @since 2023-08-05 15:08:19
 */
@Service("baseUserService")
public class BaseUserServiceImpl extends ServiceImpl<BaseUserDao, BaseUser> implements BaseUserService {
    @Resource
    BaseUserDao baseUserDao;
    @Resource
    UserLogDao userLogDao;
    @Override
    public BaseUser login(String userName, String pwd, String ip) {
        String md5Pwd = null;
        try {
            md5Pwd = MD5Utils.getMD5Str(pwd);
        } catch (NoSuchAlgorithmException e) {
            Constant.logger.error("错误:",e);
        }
        QueryWrapper<BaseUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        queryWrapper.eq("pwd",md5Pwd);
        BaseUser user = baseUserDao.selectOne(queryWrapper);

        if (user ==null)
            throw new DamPourException(DampouringExceptionEnum.PASSWORD_WRONG);
        UserLog damPourLog = new UserLog();
        damPourLog.setIpAddr(ip);
        damPourLog.setTime(TimeUtils.getNowTime());
        damPourLog.setOperator(user.getUsername());
        damPourLog.setType("login");
        userLogDao.insert(damPourLog);
        return user;
    }

    @Override
    public void logout(String userName, String ip) {
        UserLog damPourLog = new UserLog();
        damPourLog.setIpAddr(ip);
        damPourLog.setTime(TimeUtils.getNowTime());
        damPourLog.setOperator(userName);
        damPourLog.setType("logout");
        userLogDao.insert(damPourLog);
    }
}

