package com.yeyangshu.serviceorderdispatch.task;


import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.OrderRulePrice;
import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.Order;


import java.util.List;

/**
 * Task超级接口
 */
public interface ITask {

    /**
     * 设置订单条件
     *
     * @param taskConditions
     */
    public void setTaskConditions(List<TaskCondition> taskConditions);

    /**
     * 执行任务
     *
     * @param current
     * @return
     */
    public int execute(long current);

    /**
     * 获取任务id
     *
     * @return
     */
    public int getTaskId();

    /**
     * 下次执行时间
     *
     * @return
     */
    public boolean isTime();

    /**
     * 获取订单类型
     *
     * @return
     */
    public int getOrderType();

    /**
     * 推送订单
     *
     * @param order
     * @param orderRulePrice
     * @param taskCondition
     * @param round
     * @return
     */
    public boolean sendOrder(Order order, OrderRulePrice orderRulePrice, TaskCondition taskCondition, int round);

    /**
     * 订单结束
     *
     * @param order
     * @param orderRulePrice
     */
    public void taskEnd(Order order, OrderRulePrice orderRulePrice);


}