package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject.TokenRequest;

/**
 * 调用登录服务
 *
 * @author yeyangshu
 */
public interface ServicePassengerUserService {

    /**
     * 用户查询
     *
     * @param request token信息
     * @return
     */
    public ResponseResult queryPassenger(TokenRequest request);

}