package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.SystemConstantDao;
import com.example.damcurvity.entity.SystemConstant;
import com.example.damcurvity.service.SystemConstantService;
import org.springframework.stereotype.Service;

/**
 * (SystemConstant)表服务实现类
 *
 * @author makejava
 * @since 2023-08-20 08:45:28
 */
@Service("systemConstantService")
public class SystemConstantServiceImpl extends ServiceImpl<SystemConstantDao, SystemConstant> implements SystemConstantService {

}

