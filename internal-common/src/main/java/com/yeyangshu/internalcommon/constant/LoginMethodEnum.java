package com.yeyangshu.internalcommon.constant;

import lombok.Getter;

/**
 * 登录方式枚举
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 14:15
 */
public enum LoginMethodEnum {

    PASSWORD(0, "密码登录"),

    VERIFICATION_CODE(1, "短信验证码登录");

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
    LoginMethodEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
