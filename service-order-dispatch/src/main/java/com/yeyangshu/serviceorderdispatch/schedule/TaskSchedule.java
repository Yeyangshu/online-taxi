package com.yeyangshu.serviceorderdispatch.schedule;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yeyangshu.serviceorderdispatch.task.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 派单任务调度定时任务
 */
@Component
@Slf4j
public class TaskSchedule {

    /** 线程池 */
    private final ExecutorService taskExecutor = new ThreadPoolExecutor(1,
            5, 0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(3),
            new ThreadFactoryBuilder().setNameFormat(TaskSchedule.class.getSimpleName()).build());

//    @Autowired
//    private TaskManager taskManager;

    @Autowired
    private TaskStore taskStore;

//    /**
//     * 定时任务，每5秒执行一次
//     */
//    @Scheduled(cron = "0/5 * *  * * ? ")
//    public void schedule() {
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        List<Task> tasks = taskStore.getNeedRetryTask();
//
//        log.info("INFO TaskSchedule - start execute tasks, task executed every 5s, execute time:{}, task list:[{}]",
//        sdf.format(new Date()),  tasks);
//        tasks.forEach(retryTask -> {
//            taskExecutor.execute(() -> taskManager.retry(retryTask));
//        });
//    }
}