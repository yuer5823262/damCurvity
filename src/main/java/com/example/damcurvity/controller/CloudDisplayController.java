package com.example.damcurvity.controller;

import com.example.damcurvity.common.ApiRestResponse;
import com.example.damcurvity.dao.AlertInfoDao;
import com.example.damcurvity.entity.AlertInfo;
import com.example.damcurvity.service.AlertInfoService;
import com.example.damcurvity.vo.EquStateVO;
import com.example.damcurvity.vo.NodeStateVO;
import com.example.damcurvity.vo.TodayAlertCountVO;
import com.example.damcurvity.vo.TodayAlertInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api("大屏展示")
@RestController
@RequestMapping("cloudDisplay")
public class CloudDisplayController {
    @Resource
    AlertInfoDao alertInfoDao;


    @GetMapping("todayAlertInfo")
    @ApiOperation("获取今日告警信息")
    public ApiRestResponse todayAlertInfo(){
        List<TodayAlertInfoVO> todayAlertInfo =alertInfoDao.todayAlertInfo();
        return ApiRestResponse.success(todayAlertInfo);
    }


    @GetMapping("todayAlertCount")
    @ApiOperation("获取今日告警统计")
    public ApiRestResponse todayAlertCount(){
        TodayAlertCountVO todayAlertCount =alertInfoDao.todayAlertCount();
        return ApiRestResponse.success(todayAlertCount);
    }


    @GetMapping("checkEuqState")
    @ApiOperation("获取设备状态")
    public ApiRestResponse checkEuqState(){
        List<EquStateVO> equStateVO =alertInfoDao.checkEuqState();
        return ApiRestResponse.success(equStateVO);
    }

    @GetMapping("checkNodeState")
    @ApiOperation("获取节点报警状态")
    public ApiRestResponse checkNodeState(Integer stationId,String type){
        List<NodeStateVO> nodeStateVO =alertInfoDao.checkNodeState(stationId,type);
        return ApiRestResponse.success(nodeStateVO);
    }
}
