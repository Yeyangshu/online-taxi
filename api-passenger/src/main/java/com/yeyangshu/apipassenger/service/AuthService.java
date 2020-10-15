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

}