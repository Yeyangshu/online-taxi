package com.yeyangshu.internalcommon.constant;

import lombok.Getter;

/**
 * Code Enum
 *
 * @author yeyangshu
 */
public enum CommonStatusEnum {


    /**
     * 短信验证码服务 10001-10099
     */
    VERIFY_CODE_ERROR(10001, "短信验证码验证失败"),

    /**
     * 一分钟内相同验证码错误达3次，请1分钟后登录
     */
    VERIFICATION_ONE_MIN_ERROR(10002, "您同一个错误验证码点击登录的次数过多,请 1分钟后再重试"),

    /**
     * 一小时内验证码错误达3次，请10分钟后登录
     */
    VERIFICATION_TEN_MIN_ERROR(10003, "您登录失败的次数过多,请 10分钟后再重试"),

    /**
     * 一小时内验证码错误达5次，请24小时后登录
     */
    VERIFICATION_ONE_HOUR_ERROR(10004, "您今天登录失败的次数过多,请 24小时后再重试"),

    /**
     * api-passenger 乘客api 10101-10199
     */
    PHONENUMBER_EMPTY(10101, "手机号为空"),

    /**
     * 手机号格式不正确
     */
    PHONENUMBER_ERROR(10102, "手机号格式不正确"),

    /**
     * 操作成功
     */
    SUCCESS(0, "success"),

    /**
     * 操作异常
     */
    INTERNAL_SERVER_EXCEPTION(-1, "exception"),

    /**
     * 操作失败
     */
    FAIL(1, "fail");

    /**
     * code
     */
    @Getter
    private final Integer code;

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
    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    /**
     * Query by code
     *
     * @param code
     * @return
     */
    public static CommonStatusEnum getByCode(int code) {
        for (CommonStatusEnum c : values()) {
            if (c.getCode() == code) {
                return c;
            }
        }
        return null;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>value</tt>.
     *
     * @return property value of value
     */
    public String getValue() {
        return value;
    }
}