package com.yeyangshu.serviceorderdispatch.schedule;

import com.yeyangshu.serviceorderdispatch.task.ITask;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 订单任务存储类
 */
@Component
@Data
@Slf4j
public class TaskStore {

    /**
     * 同步容器，存储任务
     */
    private final ConcurrentHashMap<Integer, ITask> results = new ConcurrentHashMap<>();

    /**
     * 订单添加任务
     *
     * @param taskId 订单号
     * @param task   具体子任务实现
     */
    public void addTask(int taskId, ITask task) {
        results.put(taskId, task);
    }

    /**
     * 获取需要重试订单
     *
     * @return 未派送订单
     */
    public List<ITask> getNeedRetryTask() {
        synchronized (results) {
            List<ITask> list = new ArrayList<>(results.values());
            results.clear();
            return list;
        }
    }
}