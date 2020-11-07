package com.yeyangshu.internalcommon.constant;

import lombok.Getter;

/**
 * 支付方式枚举
 */
@Getter
public enum PayTypeEnum {

    /**
     * 微信支付
     */
    WXPAY(1, "微信支付"),

    /**
     * 余额支付
     */
    BALANCE(2, "余额支付"),

    /**
     * 平台账户
     */
    SYSTEM(3, "平台账户"),

    /**
     * 支付宝支付
     */
    ALIPAY(4, "支付宝支付"),

    /**
     * none
     */
    EX(999, "none");

    private final int code;
    private final String value;

    PayTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

}