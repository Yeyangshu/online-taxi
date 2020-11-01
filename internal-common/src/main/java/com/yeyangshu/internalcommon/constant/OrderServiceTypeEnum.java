package com.yeyangshu.internalcommon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单服务类型枚举
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 14:15
 */
@Getter
public enum OrderServiceTypeEnum {

    /**
     * 实时订单
     */
    REAL_TIME(1, "实时订单"),

    /**
     * 预约订单
     */
    MAKE_AN_APPOINTMENT(2, "预约订单"),

    /**
     * 接机
     */
    PICK_UP(3, "接机"),

    /**
     * 送机
     */
    SEND_MACHINE(4, "送机"),

    /**
     * 半日租
     */
    CHARTERED_CAR(5, "半日租"),
    /**
     * 全日租
     */
    THROUGHOUT_THE_DAY(6, "全日租");

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
    OrderServiceTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
