package com.yeyangshu.internalcommon.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 身份类型
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 10:14
 */
@Getter
@AllArgsConstructor
public enum IdentityEnum {

    /**
     * 乘客
     */
    PASSENGER(1, "乘客"),

    /**
     * 司机
     */
    DRIVER(2, "司机"),

    /**
     * 车机
     */
    CAR_SCREEN(3, "车机"),

    /**
     * 大屏
     */
    LARGE_SCREEN(4, "大屏"),

    /**
     *
     */
    OTHER(9, "其他身份");

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
    IdentityEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
