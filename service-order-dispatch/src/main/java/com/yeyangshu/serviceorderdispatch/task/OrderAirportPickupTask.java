package com.yeyangshu.serviceorderdispatch.task;

/**
 * 送机任务
 */
public class OrderAirportPickupTask extends OrderNormalTask {
    public OrderAirportPickupTask(int orderId, int type) {
        super(orderId, type);
    }
}