package com.yeyangshu.serviceorderdispatch.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yeyangshu.internalcommon.constant.DispatchConstant;
import com.yeyangshu.internalcommon.constant.OrderTypeEnum;
import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.Order;
import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.OrderRulePrice;
import com.yeyangshu.internalcommon.entity.serviceorderdispatch.dataobject.TagInfo;
import com.yeyangshu.serviceorderdispatch.service.DispatchService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 基础订单
 * Task = 时间 + 事件 + 轮数
 */
@Data
@Slf4j
public abstract class AbstractTask implements Task {

    /** 订单id */
    //protected int orderId;

    /** 任务条件集合 */
    protected List<TaskCondition> taskConditions;

    /** 任务下次执行时间 */
    protected long nextExecuteTime;

    /** 任务轮数 */
    protected int taskRound;

    /** 任务状态 */
    protected int taskStatus;

    /** 任务结束 */
    protected static final int TASK_STATUS_END = -1;


    @Override
    public Task create() {
        TaskThreadContext taskContext = (TaskThreadContext) TaskContextHolder.getTaskContext();
        TaskFactory taskFactory = taskContext.getTaskFactory();
        return null;
    }

    @Override
    public int execute() {
        TaskThreadContext taskContext = (TaskThreadContext) TaskContextHolder.getTaskContext();

        long startTime = taskContext.getStartTime();
        if (startTime < nextExecuteTime) {
            return taskStatus;
        }

        int orderId = taskContext.getOrderId();
        Order order = DispatchService.ins().getOrderById(orderId);
        OrderRulePrice orderRulePrice = DispatchService.ins().getOrderRulePrice(orderId);

        // 订单是否存在
        if (isExistOrder(order, orderRulePrice)) {
            setTaskStatusEnd();
            return taskStatus;
        }

        // 订单是否被司机接单
        if (isOrderSent(order)) {
            setTaskStatusEnd();
            return taskStatus;
        }

        // 是否推送完轮数
        if (isEndRound()) {
            setTaskStatusEnd();
            end();
            return taskStatus;
        }

        // getCondition
        // nextExecuteTime = current + TimeUnit.SECONDS.toMillis(taskCondition.getNextTime());
        // 是否派单成功，没成功继续，成功返回
        if (!isOrderSentSuccess(order, orderRulePrice, startTime)) {
            return execute();
        }
        return taskStatus;
    }

    @Override
    public void end() {

    }

    /**
     * 设置任务结束状态
     */
    public void setTaskStatusEnd() {
        taskStatus = TASK_STATUS_END;
    }

    /**
     * 判断订单是否存在
     *
     * @return 订单是否存在
     */
    public boolean isExistOrder(Order order, OrderRulePrice orderRulePrice) {
        return order != null && orderRulePrice != null;
    }

    /**
     * 订单是否被司机接受
     *
     * @param order 订单
     * @return 结果
     */
    public boolean isOrderSent(Order order) {
        return order.getStatus() != DispatchConstant.ORDER_STATUS_ORDER_START;
    }

    /**
     * 是否完成所有轮数
     *
     * @return 结果
     */
    public boolean isEndRound() {
        return taskRound > taskConditions.size() - 1;
    }

    /**
     * 是否发送订单成功
     *
     * @param order
     * @param orderRulePrice
     * @return
     */
    public boolean isOrderSentSuccess(Order order, OrderRulePrice orderRulePrice, long startTime) {
        int currentRound = taskRound;
        taskRound++;
        TaskCondition taskCondition = taskConditions.get(currentRound);
        nextExecuteTime = startTime + TimeUnit.SECONDS.toMillis(taskCondition.getNextTime());
        return sendOrder(order, orderRulePrice, taskCondition, currentRound);
    }

    public boolean sendOrder(Order order, OrderRulePrice orderRulePrice, TaskCondition taskCondition, int round) {
        return true;
    }
}