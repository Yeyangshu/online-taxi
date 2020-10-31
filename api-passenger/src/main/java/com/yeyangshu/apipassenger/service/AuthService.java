package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;

/**
 * 登录验证服务
 *
 * @author yeyangshu
 */
public interface AuthService {

    /**
     * 登录验证
     *
     * @param passengerPhone
     * @param code
     * @return
     */
    public ResponseResult auth(String passengerPhone, String code);


    /**
     * 生成验证码
     *
     * @param phoneNum 手机号
     * @return string
     */

    String createToken(String phoneNum);

    /**
     * 检查验证码
     *
     * @param token token
     * @return string
     */
    String checkToken(String token);

    /**
     * 删除token
     *
     * @param subject subject
     */
    void deleteToken(String subject);

}