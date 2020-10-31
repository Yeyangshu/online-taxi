package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.TokenRequest;

/**
 * 登录验证服务
 *
 * @author yeyangshu
 */
public interface AuthService {

    /**
     * 登录验证
     *
     * @param request
     * @return
     */
    ResponseResult auth(TokenRequest request);

}