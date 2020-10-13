package com.yeyangshu.serviceverificationcode.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;

/**
 * 验证码服务
 *
 * @author yeyangshu
 */
public interface VerifyCodeService {

    /**
     * 根据身份和手机号生成验证码
     *
     * @param identity
     * @param phoneNumber
     * @return
     */
    public ResponseResult<VerifyCodeResponse> generate(int identity, String phoneNumber);

    /**
     * 校验身份，手机号，验证码的合法性
     *
     * @param identity
     * @param phoneNumber
     * @param code
     * @return
     */
    public ResponseResult verifyCode(int identity, String phoneNumber, String code);
}