package com.yeyangshu.serviceorderdispatch.schedule;

import com.yeyangshu.serviceorderdispatch.task.ITask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 派单定时任务
 */
@Component
@Slf4j
public class TaskSchedule {

    @Autowired
    private TaskStore taskStore;

    @Autowired
    private TaskManager taskManager;

    /**
     * 定时任务，每5秒执行一次
     */
    @Scheduled(cron = "0/5 * *  * * ? ")
    public void schedule() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<ITask> tasks = taskStore.getNeedRetryTask();

        log.info("INFO TaskSchedule - start execute tasks, task executed every 5s, execute time:{}, task list:[{}]",
                sdf.format(new Date()),  tasks);
        tasks.forEach(it -> taskManager.retry(it));
    }
}