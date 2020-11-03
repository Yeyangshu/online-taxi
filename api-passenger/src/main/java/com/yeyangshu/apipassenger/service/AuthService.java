package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject.TokenRequest;

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