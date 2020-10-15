package com.yeyangshu.servicepassengeruser.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;

/**
 * 用户登录服务
 *
 * @author yeyangshu
 */
public interface PassengerUserService {

    /**
     * 用户登录
     *
     * @param passengerPhone
     * @return
     */
    public ResponseResult login(String passengerPhone);

    /**
     * 用户下线
     *
     * @param token
     * @return
     */
    public ResponseResult logout(String token);
}