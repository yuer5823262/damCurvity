package com.example.damcurvity.StaticSchedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.damcurvity.entity.AlertInfo;
import com.example.damcurvity.entity.BaseAlertManager;
import com.example.damcurvity.entity.BaseStation;
import com.example.damcurvity.entity.InfoStaionNodeXz;
import com.example.damcurvity.que.InfoStationNodeXzQue;
import com.example.damcurvity.service.BaseAlertManagerService;
import com.example.damcurvity.service.BaseStationService;
import com.example.damcurvity.service.InfoStaionNodeXzService;
import com.example.damcurvity.service.InfoStationNodeService;
import com.example.damcurvity.vo.InfoStationNodeChangeRateVO;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ConstantScheduleTask {
    @Resource
    BaseStationService baseStationService;
    @Resource
    InfoStationNodeService infoStationNodeService;
    @Resource
    InfoStaionNodeXzService infoStaionNodeXzService;
    @Resource
    BaseAlertManagerService baseAlertManagerService;
    @Scheduled(cron="5 0 0 * * ?")
    //@Scheduled(fixedRate = 1000*60*60*24)
    public void DeviceDataXz()
    {
        List<BaseStation> baseStations = baseStationService.list();
        for (BaseStation baseStation : baseStations) {
            if (baseStation.getState()!=null && baseStation.getState() != 0)
            {
                InfoStationNodeXzQue infoStationNodeXzQue = new InfoStationNodeXzQue();
                infoStationNodeXzQue.setInterval(baseStation.getExceptionScreeningInterval());
                infoStationNodeXzQue.setZx(baseStation.getConfidenceInterval());
                infoStationNodeXzQue.setStationId(baseStation.getId());
                List<InfoStaionNodeXz> infoStationNodeList = infoStationNodeService.selectByIntervalXz(infoStationNodeXzQue);
                infoStaionNodeXzService.saveBatch(infoStationNodeList);
            }
        }
    }


    @Scheduled(cron="0 59 23 * * ?")
    public void change_rate()
    {
        List<InfoStationNodeChangeRateVO> infoStationNodeChangeRateVOS = infoStationNodeService.change_rage();
        for (InfoStationNodeChangeRateVO infoStationNodeChangeRateVO : infoStationNodeChangeRateVOS) {
            QueryWrapper<BaseAlertManager> baseAlertManagerQueryWrapper = new QueryWrapper<>();
            baseAlertManagerQueryWrapper.eq("station_id",infoStationNodeChangeRateVO.getStationId());
            BaseAlertManager one = baseAlertManagerService.getOne(baseAlertManagerQueryWrapper);
            AlertInfo alertInfo = one.checkChangeAlertX(infoStationNodeChangeRateVO);
            if(alertInfo !=null)
            {
                alertInfo.insert();
            }
            alertInfo = one.checkChangeAlertY(infoStationNodeChangeRateVO);
            if(alertInfo !=null)
            {
                alertInfo.insert();
            }
        }
    }
}
