package com.yeyangshu.serviceorderdispatch.task;


import com.yeyangshu.internalcommon.constant.OrderTypeEnum;

/**
 * 任务工厂
 */
public class OrderTaskFactory {

    public static ITask createTask(int orderId, int serviceTypeId, int type) {
        if (serviceTypeId == OrderTypeEnum.APPOINTMENT.getCode()) {
            return new OrderNormalTask(orderId, type);
        } else if (serviceTypeId == OrderTypeEnum.AIRPORT_PICKUP.getCode()) {
            return new OrderAirportPickupTask(orderId, type);
        } else if (serviceTypeId == OrderTypeEnum.AIRPORT_DROPOFF.getCode()) {
            return new OrderAirportDropoffTask(orderId, type);
        } else {
            return new OrderNormalTask(orderId, type);
        }
    }
}