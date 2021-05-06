package com.yeyangshu.serviceorderdispatch.task;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/12/19 0:33
 */
public class TaskThreadContext implements ThreadContext {

    /** 订单id */
    private int orderId;

    private TaskFactory taskFactory;

    /** 任务开始执行时间 */
    private long startTime;

    /** task context map */
    public static Map<String, Object> taskContextMap = new HashMap<>();

    /**
     * 添加
     *
     * @param key
     * @param object
     */
    @Override
    public void add(String key, Object object) {
        if (null == taskContextMap) {
            taskContextMap = new HashMap<String, Object>();
        }
        taskContextMap.put(key, object);
    }

    @Override
    public Object get(String key) {
        if (null == taskContextMap) {
            taskContextMap = new HashMap<String, Object>();
        }
        return taskContextMap.get(key);
    }

    @Override
    public Map<String, Object> getAll() {
        if (null == taskContextMap) {
            taskContextMap = new HashMap<String, Object>();
        }
        return taskContextMap;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public TaskFactory getTaskFactory() {
        return taskFactory;
    }

    public void setTaskFactory(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
