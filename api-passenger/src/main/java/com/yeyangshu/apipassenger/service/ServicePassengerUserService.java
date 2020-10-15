package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;

/**
 * 调用登录服务
 *
 * @author yeyangshu
 */
public interface ServicePassengerUserService {

    /**
     * 手机登录
     *
     * @param passengerPhone
     * @return
     */
    public ResponseResult login(String passengerPhone);

}