package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;

/**
 * 获取/验证验证码服务
 */
public interface VerificationCodeService {

    /**
     * 用户获取验证码
     * @param phoneNumber
     * @return
     */
    public ResponseResult send(String phoneNumber);
}