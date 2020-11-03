package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;

/**
 * 调用生成短信验证码服务
 *
 * @author yeyangshu
 */
public interface ServiceVerificationCodeRestTemplateService {

    /**
     * 生成短信验证码
     *
     * @param identity
     * @param phoneNumber
     * @return
     */
    public ResponseResult generateCode(int identity, String phoneNumber);

    /**
     * 验证短信验证码
     *
     * @param identity
     * @param phoneNumber
     * @param code
     * @return
     */
    public ResponseResult verifyCode(int identity, String phoneNumber, String code);
}