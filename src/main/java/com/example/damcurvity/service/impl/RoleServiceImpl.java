package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.RoleDao;
import com.example.damcurvity.entity.Role;
import com.example.damcurvity.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2023-09-24 16:14:01
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}

