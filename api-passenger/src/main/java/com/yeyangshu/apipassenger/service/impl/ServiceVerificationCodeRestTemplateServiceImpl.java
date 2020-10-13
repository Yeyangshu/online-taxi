package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.ServiceVerificationCodeRestTemplateService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 调用service-verification-code服务
 *
 */
@Service
public class ServiceVerificationCodeRestTemplateServiceImpl implements ServiceVerificationCodeRestTemplateService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 调用service-verification-code生成验证码
     * @param identity
     * @param phoneNumber
     * @return
     */
    @Override
    public ResponseResult generateCode(int identity, String phoneNumber) {
        String url = "http://service-verification-code/verify-code/generate/" + identity + "/" + phoneNumber;
        ResponseResult responseResult = restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(null, null), ResponseResult.class).getBody();
        return responseResult;
    }
}