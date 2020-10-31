package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.dto.apipassenger.request.TokenRequest;
import com.yeyangshu.internalcommon.dto.ResponseResult;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 10:11
 */
public interface PassengerRegisterHandleService {

    /**
     * 乘客登陆接口
     */
    ResponseResult signIn(TokenRequest request);

    /**
     * 乘客登出接口
     */
    ResponseResult signOut(TokenRequest request) throws Exception;
}
