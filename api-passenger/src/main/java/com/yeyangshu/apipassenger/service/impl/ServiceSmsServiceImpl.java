package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.ServiceSmsService;
import com.yeyangshu.internalcommon.entity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 调用第三方短信服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 21:21
 */
@Service
public class ServiceSmsServiceImpl implements ServiceSmsService {

    @Autowired
    RestTemplate restTemplate;


    @Override
    public ResponseResult sendSms(String phoneNumber, String code) {
        return ResponseResult.success("code");
    }
}
