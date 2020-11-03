package com.yeyangshu.servicesms.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.servicesms.datatransferobject.SmsSendRequest;

/**
 * 第三方短信发送服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 21:40
 */
public interface SmsService {

    /**
     * 第三方发送短信
     * @return
     */
    public ResponseResult sendSms(SmsSendRequest smsSendRequest);
}
