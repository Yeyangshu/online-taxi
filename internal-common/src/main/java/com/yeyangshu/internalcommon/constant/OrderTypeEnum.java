package com.yeyangshu.internalcommon.constant;

import lombok.Getter;

/**
 * 订单类型
 */
public enum OrderTypeEnum {
    /**
     * 实时单
     */
    REAL_TIME(1, "实时单"),

    /**
     * 预约单
     */
    APPOINTMENT(2, "预约单"),

    /**
     * 接机
     */
    AIRPORT_PICKUP(3, "接机"),

    /**
     * 送机
     */
    AIRPORT_DROPOFF(4, "送机"),

    /**
     * 包车半日
     */
    CHARTERED_CAR_HALF(5, "包车半日"),

    /**
     * 包车全日
     */
    CHARTERED_CAR_FULL(6, "包车全日"),

    /**
     * 强派
     */
    FORCE(1, "强派"),

    /**
     * 普通
     */
    NORMAL(2, "普通"),

    /**
     * 特殊
     */
    SPECIAL(3, "特殊");

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
    OrderTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

}