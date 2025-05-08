package com.example.damcurvity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.damcurvity.dao.BaseProjectDao;
import com.example.damcurvity.entity.BaseProject;
import com.example.damcurvity.service.BaseProjectService;
import org.springframework.stereotype.Service;

/**
 * (BaseProject)表服务实现类
 *
 * @author makejava
 * @since 2023-08-05 16:43:53
 */
@Service("baseProjectService")
public class BaseProjectServiceImpl extends ServiceImpl<BaseProjectDao, BaseProject> implements BaseProjectService {

}

