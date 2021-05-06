package com.yeyangshu.serviceorderdispatch.task;

import java.util.Map;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/12/19 0:58
 */
public class TaskContextHolder {

    /** task context threadLocal */
    private static final ThreadLocal<ThreadContext> TASK_CONTEXT = new ThreadLocal<>();

    public static ThreadContext getTaskContext() {
        if (TASK_CONTEXT.get() == null) {
            TASK_CONTEXT.set(new TaskThreadContext());
        }
        return TASK_CONTEXT.get();
    }

    public static void setTaskContext(ThreadContext taskContext) {
        TASK_CONTEXT.set(taskContext);
    }

    public static void add(String key, Object param) {
        getTaskContext().add(key, param);
    }

    public static Object get(String key) {
        return getTaskContext().get(key);
    }

    public static Map<String, Object> getAll() {
        return getTaskContext().getAll();
    }

    public static void main(String[] args) {
        // 创建线程t1
        Thread thread = new Thread(() -> {
            // 添加上下文
            TaskThreadContext context = new TaskThreadContext();
            context.setOrderId(1);
            TaskContextHolder.setTaskContext(context);
            // 添加普通属性
            TaskContextHolder.add("t1", "Hello t1");

            TaskThreadContext taskContext = (TaskThreadContext) TaskContextHolder.getTaskContext();
            System.out.println("t1 order id：" + taskContext.getOrderId());
            System.out.println("t1：" + TaskContextHolder.get("t1"));
        }, "t1");
        thread.start();

        // 创建线程t2
        Thread thread2 = new Thread(() -> {
            // 添加上下文
            TaskThreadContext context = new TaskThreadContext();
            context.setOrderId(2);
            TaskContextHolder.setTaskContext(context);
            // 添加普通属性
            TaskContextHolder.add("t2", "Hello t2");

            TaskThreadContext taskContext = (TaskThreadContext) TaskContextHolder.getTaskContext();
            System.out.println("t2 order id：" + taskContext.getOrderId());
            System.out.println("t2：" + TaskContextHolder.get("t2"));
        }, "t2");
        thread2.start();
    }
}
