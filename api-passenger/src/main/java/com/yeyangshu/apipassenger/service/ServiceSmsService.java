package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;

/**
 * 第三方短信服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 21:19
 */
public interface ServiceSmsService {

    /**
     * 发送短信
     * @param phoneNumber
     * @param code
     * @return
     */
    public ResponseResult sendSms(String phoneNumber, String code);
}
