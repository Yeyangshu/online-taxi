package com.yeyangshu.internalcommon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 */
@Getter
public enum OrderStatusEnum {
    /**
     * 预估订单
     */
    CALL_ORDER_FORECAST(0, "预估订单"),
    /**
     * 订单开始
     */
    STATUS_ORDER_START(1, "订单开始"),
    /**
     * 司机接单
     */
    STATUS_DRIVER_ACCEPT(2, "司机接单"),
    /**
     * 去接乘客
     */
    STATUS_RESERVED_ORDER_TO_PICK_UP(3, "去接乘客"),
    /**
     * 司机到达乘客起点
     */
    STATUS_DRIVER_ARRIVED(4, "司机到达乘客起点"),
    /**
     * 乘客上车，司机开始行程
     */
    STATUS_DRIVER_TRAVEL_START(5, "乘客上车，司机开始行程"),
    /**
     * 到达目的地，行程结束，未支付
     */
    STATUS_DRIVER_TRAVEL_END(6, "到达目的地，行程结束，未支付"),
    /**
     * 发起收款
     */
    STATUS_PAY_START(7, "发起收款"),
    /**
     * 支付完成
     */
    STATUS_PAY_END(8, "支付完成");

    /**
     * code
     */
    @Getter
    private final int code;

    /**
     * value
     */
    @Getter
    private final String value;

    /**
     * 构造函数
     *
     * @param code
     * @param value
     */
    OrderStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}