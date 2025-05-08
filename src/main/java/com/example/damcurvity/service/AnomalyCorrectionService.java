package com.example.damcurvity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.damcurvity.entity.AnomalyCorrection;

/**
 * (AnomalyCorrection)表服务接口
 *
 * @author makejava
 * @since 2023-09-03 18:58:34
 */
public interface AnomalyCorrectionService extends IService<AnomalyCorrection> {

    Boolean addAnomalyCorrection(AnomalyCorrection anomalyCorrection);
}

