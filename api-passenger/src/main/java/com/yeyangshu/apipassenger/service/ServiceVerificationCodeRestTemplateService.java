package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;

/**
 * 调用生成验证码服务
 *
 * @author yeyangshu
 */
public interface ServiceVerificationCodeRestTemplateService {

    /**
     * 生成验证码
     * @param identity
     * @param phoneNumber
     * @return
     */
    public ResponseResult generateCode(int identity, String phoneNumber);
}