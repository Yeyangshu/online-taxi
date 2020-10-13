package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.ServiceSmsRestTemplateService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * 调用第三方短信服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 21:21
 */
public class ServiceSmsRestTemplateServiceImpl implements ServiceSmsRestTemplateService {

    @Autowired
    RestTemplate restTemplate;


    @Override
    public ResponseResult sendSms(String phoneNumber, String code) {
        return null;
    }
}
