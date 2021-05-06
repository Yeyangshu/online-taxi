package com.yeyangshu.serviceorderdispatch.task;

/**
 * Task超级接口
 */
public interface Task {

    /**
     * 创建任务
     *
     * @param taskContextHolder
     * @return task
     */
    public Task create();

    /**
     * 执行任务
     *
     * @param taskContextHolder 任务上下文
     * @return
     */
    public int execute();

    /**
     * 结束任务
     *
     * @param taskContextHolder 任务上下文
     */
    public void end();

}