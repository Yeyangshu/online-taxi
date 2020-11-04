package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.ServiceVerificationCodeService;
import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.serviceverificationcode.datatransferobject.VerifyCodeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 调用service-verification-code服务
 *
 * @author yeyangshu
 */
@Service
public class ServiceVerificationCodeServiceImpl implements ServiceVerificationCodeService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 调用service-verification-code生成短信验证码
     *
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

    /**
     * 调用service-verification-code验证短信验证码
     *
     * @param identity
     * @param phoneNumber
     * @param code
     * @return
     */
    @Override
    public ResponseResult verifyCode(int identity, String phoneNumber, String code) {
        String url = "http://service-verification-code/verify-code/verify/";
        VerifyCodeRequest request = new VerifyCodeRequest();
        request.setCode(code);
        request.setIdentity(identity);
        request.setPhoneNumber(phoneNumber);
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(request, null), ResponseResult.class).getBody();
        return result;
    }
}