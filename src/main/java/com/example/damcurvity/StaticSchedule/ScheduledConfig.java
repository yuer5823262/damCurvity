package com.example.damcurvity.StaticSchedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduledConfig implements SchedulingConfigurer {
    //笔者这里配合数据库来实现动态添加。方便查询数据库这里使用JdbcTemplate
    Integer c=11;
    private ScheduledTaskRegistrar taskRegistrar;
    private Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();

    /**
     * 这个方法在Spring初始化的时候会帮我们执行，这里也会拉取数据库内需要执行的任务，进行添加到定时器里。
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler);
        this.taskRegistrar = scheduledTaskRegistrar;
    }
    public void addTask(String taskId, TriggerTask triggerTask) {
        //如果定时任务id已存在，则取消原定时器，从新创建新定时器，这里也是个更新定时任务的过程。
        if (taskFutures.containsKey(taskId)) {
            cancelTriggerTask(taskId);
        }
        TaskScheduler scheduler = taskRegistrar.getScheduler();
        ScheduledFuture<?> future = scheduler.schedule(triggerTask.getRunnable(), triggerTask.getTrigger());
        taskFutures.put(taskId, future);
    }

    /**
     * 取消任务
     */
    public void cancelTriggerTask(String taskId) {
        ScheduledFuture<?> future = taskFutures.get(taskId);
        if (future != null) {
            future.cancel(true);
        }
        taskFutures.remove(taskId);
    }

    public void cancelTriggerTaskAll() {
        for(Map.Entry<String, ScheduledFuture<?>> entry : taskFutures.entrySet()){
            entry.getValue().cancel(true);
        }
        taskFutures.clear();
    }
}
