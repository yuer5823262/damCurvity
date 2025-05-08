package com.example.damcurvity.StaticSchedule;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.damcurvity.common.Constant;
import com.example.damcurvity.dao.*;
import com.example.damcurvity.entity.*;
import com.example.damcurvity.service.AlertInfoService;
import com.example.damcurvity.service.BaseAlertManagerService;
import com.example.damcurvity.service.BaseStationNodeService;
import com.example.damcurvity.service.StationInitValueService;
import com.example.damcurvity.util.DeviceUtils;
import com.example.damcurvity.util.TimeUtils;
import com.example.damcurvity.util.copyUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class DataGetScheduleTask implements SchedulingConfigurer {
    @Resource
    BaseEquipmentDao baseEquipmentDao;
    @Resource
    InfoStationNodeDao infoStationNodeDao;
    @Resource
    BaseAcquisitionEquDao baseAcquisitionEquDao;
    @Resource
    BaseStationDao baseStationDao;
    @Resource
    BaseAlertManagerService baseAlertManagerService;
    @Resource
    StationInitValueService stationInitValueService;
    @Resource
    BaseStationNodeService baseStationNodeService;
    @Resource
    AlertInfoService alertInfoService;
    ScheduledTaskRegistrar taskRegistrar;

    private final Map<Integer, ScheduledTask> taskMap = new ConcurrentHashMap<>();
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        QueryWrapper<BaseEquipment> queryWrapper = new QueryWrapper<>();
//        List<BaseEquipment> baseEquipmentList = baseEquipmentDao.selectList(queryWrapper);
//        for (BaseEquipment baseEquipment:baseEquipmentList)
//        {
//            EquipmentDataGetter equipmentDataGet = new EquipmentDataGetter(baseEquipment);
//            taskRegistrar.addTriggerTask(equipmentDataGet,scheduledConfig-> equipmentDataGet.nextTime);
//        }
//
//    }
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
        QueryWrapper<BaseStation> baseStationQueryWrapper = new QueryWrapper<>();
        List<BaseStation> baseStationList = baseStationDao.selectList(baseStationQueryWrapper);
        for(BaseStation baseStation:baseStationList)
        {
            addTask(baseStation);
        }
    }
    // 添加定时任务
    public void addTask(BaseStation baseStation) {
        EquipmentDataGetter equipmentDataGet = new EquipmentDataGetter(baseStation);
        TriggerTask triggerTask = new TriggerTask(equipmentDataGet, scheduledConfig-> equipmentDataGet.nextTime);
        ScheduledTask scheduledFuture = taskRegistrar.scheduleTriggerTask(triggerTask);
        taskMap.put(baseStation.getId(), scheduledFuture);
        Constant.logger.info("添加采集任务:"+baseStation.getName());
    }

    // 删除定时任务
    public void deleteTask(Integer equipmentId) {
        ScheduledTask scheduledFuture = taskMap.get(equipmentId);
        if (scheduledFuture != null) {
            scheduledFuture.cancel();
            taskMap.remove(equipmentId);
        }
    }
//    (a)->{
//        // 此处可以书写数据库访问逻辑
//        // String cron = tempMapper.selectById(1);
//        // return new CronTrigger(cron).nextExecutionTime(a);
//        return new CronTrigger("0/3 * * * * ?").nextExecutionTime(a);
//    }
class EquipmentDataGetter implements Runnable
{
    private BaseStation baseStation;
    private final DeviceUtils deviceUtils;
    private final BaseEquipment baseEquipment;
    public Date nextTime;
    EquipmentDataGetter(BaseStation baseStation)
    {
        nextTime = new Date();
        nextTime.setTime(nextTime.getTime()+10000);
        this.baseStation=baseStation;
        BaseEquipment baseEquipment = baseEquipmentDao.selectById(baseStation.getMonitorEquId());
        BaseAcquisitionEqu baseAcquisitionEqu = baseAcquisitionEquDao.selectById(baseEquipment.getAcquId());
        this.baseEquipment = baseEquipment;
        this.deviceUtils = DeviceUtils.getDeviceUtil(baseAcquisitionEqu.getDk());
        if(!deviceUtils.portIsOpen()) return;
        if(this.deviceUtils.setUploadModel(baseEquipment.getSerialNumber(),"0"))
        {
            this.deviceUtils.setSave();
        }
        else
        {
            Constant.logger.error("设置上传模式失败");
        }

    }
    void checkStationNode(Integer stationId,List<BaseStationNode> nodesByDeviceNumber)
    {
        nodesByDeviceNumber.forEach(t->t.setStationId(stationId));
        QueryWrapper<BaseStationNode> baseStationNodeQueryWrapper = new QueryWrapper<>();
        baseStationNodeQueryWrapper.eq("station_id",stationId);
        if(baseStationNodeService.list(baseStationNodeQueryWrapper).size() != nodesByDeviceNumber.size())
        {
            baseStationNodeService.remove(baseStationNodeQueryWrapper);
            baseStationNodeService.saveBatch(nodesByDeviceNumber);
        }
    }
    List<StationInitValue> checkInitValue(Integer stationId,List<InfoStationNode> infoStationNodes)
    {
        if(infoStationNodes.size()==0)
            return new ArrayList<>();
        QueryWrapper<StationInitValue> stationInitValueQueryWrapper = new QueryWrapper<>();
        stationInitValueQueryWrapper.eq("station_id",stationId);
        List<StationInitValue> stationInitValues = stationInitValueService.list(stationInitValueQueryWrapper);
        if(stationInitValues.size()==0)
        {
            for(InfoStationNode infoStationNode:infoStationNodes)
            {
                StationInitValue stationInitValue = new StationInitValue();
                copyUtils.copyPropertiesIgnoreNull(infoStationNode,stationInitValue);
                stationInitValues.add(stationInitValue);
            }
            stationInitValueService.saveBatch(stationInitValues);
            return new ArrayList<>();
        }
         return stationInitValues;

    }
    @Override
    public void run() {
//        baseEquipment = baseEquipmentDao.s(baseEquipment.getId());
//        QueryWrapper<BaseStation> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("monitor_id",baseEquipment.getId());
        BaseStation baseStation = baseStationDao.selectById(this.baseStation.getId());
        this.baseStation = baseStation;
        Date date1 = new Date();
        long time = date1.getTime();
        long newTime = time + baseStation.getJg()* 60000L;
        this.nextTime = new Date(newTime);

        if(!deviceUtils.portIsOpen() || baseStation.getState()==null || baseStation.getState() == 0)
            return;
        Constant.logger.info(baseEquipment.getName()+"开始获取数据");
        String curTime = TimeUtils.getNowTime();
        String equNumber = baseEquipment.getSerialNumber();
        List<InfoStationNode> infoStationNodes = new ArrayList<>();
        List<BaseStationNode> nodesByDeviceNumber = deviceUtils.getNodesByDeviceNumber(equNumber);
        if(nodesByDeviceNumber.size() != 0) this.checkStationNode(baseStation.getId(),nodesByDeviceNumber);
        for(BaseStationNode baseStationNode:nodesByDeviceNumber)
        {
            InfoStationNode data = deviceUtils.getData(equNumber, baseStationNode.getName());
            if(data!=null) {
                data.setTime(curTime);
                infoStationNodes.add(data);
            }
        }

        if(infoStationNodes.size()==0)
            return;
        infoStationNodes.forEach(t->t.setStationId(baseStation.getId()));
        Constant.logger.info("set stationId::::::::::"+baseStation.getId());
        infoStationNodeDao.insertBatch(infoStationNodes);
        List<StationInitValue> stationInitValues = this.checkInitValue(baseStation.getId(),infoStationNodes);
        if(baseStation.getIsAlert()!=0)
        {
            QueryWrapper<BaseAlertManager> baseAlertManagerQueryWrapper = new QueryWrapper<>();
            baseAlertManagerQueryWrapper.eq("station_id",baseStation.getId());
            BaseAlertManager baseAlertManager = baseAlertManagerService.getOne(baseAlertManagerQueryWrapper);
            if(baseAlertManager!=null && stationInitValues.size()!=0)
            {
                List<AlertInfo> result = baseAlertManager.checkAlert(stationInitValues,infoStationNodes,baseStation);

                alertInfoService.saveBatch(result);
                AlertInfo alertInfo = baseEquipment.checkVol(infoStationNodes.get(0));
                if( alertInfo!= null)
                    alertInfoService.save(alertInfo);

            }

        }


        Constant.logger.info(baseEquipment.getName()+"采集完毕");
    }
}
}


