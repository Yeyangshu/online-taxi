package com.yeyangshu.internalcommon.constant;

import lombok.Getter;

/**
 * 电话号码状态码
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 9:42
 */
@Getter
public enum PhoneStatusCodeEnum {

    /**
     * 校验码：2000-2099
     */
    PHONE_NUM_EMPTY(2000, "手机号为空"),
    ENCRYPT_EMPTY(2001, "密文为空"),
    PHONE_NUM_DIGIT(2003, "手机号不为11位"),
    PHONE_NUM_ERROR(2004, "手机号不正确"),
    TOKEN_IS_EMPTY(2005, "Token为空!"),
    PHONE_NUM_REPEAT(2006, "手机号已添加过");

    /**
     * 返回码
     */
    private final int code;

    /**
     * 返回信息
     */
    private final String value;

    PhoneStatusCodeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
