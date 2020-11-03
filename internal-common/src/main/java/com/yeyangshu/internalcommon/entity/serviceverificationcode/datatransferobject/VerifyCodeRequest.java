package com.yeyangshu.internalcommon.entity.serviceverificationcode.datatransferobject;

import lombok.Data;

/**
 * 验证码校验信息接收类
 *
 * @author yeyangshu
 */
@Data
public class VerifyCodeRequest {

    /**
     * 身份信息
     */
    private int identity;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 验证码
     */
    private String code;
}