package com.yeyangshu.serviceorderdispatch.task;

/**
 * 抽象派单规则
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/12/9 22:19
 */
public interface AbstractDispatchRule {

    /**
     * 创建对应任务
     *
     * @param orderId 订单id
     * @param serviceTypeId 服务类型id
     * @return 派单任务
     */
    public abstract Task createDispatchTask(int orderId, int serviceTypeId);

}
