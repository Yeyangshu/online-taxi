package com.yeyangshu.apipassenger.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yeyangshu.apipassenger.service.ServiceSmsRestTemplateService;
import com.yeyangshu.apipassenger.service.VerificationCodeService;
import com.yeyangshu.internalcommon.constant.CommonStatusEnum;
import com.yeyangshu.internalcommon.constant.IdentityConstant;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    ServiceVerificationCodeRestTemplateServiceImpl serviceVerificationCodeRestTemplateService;

    @Autowired
    ServiceSmsRestTemplateService serviceSmsRestTemplateService;

    /**
     * 第三方发送短信
     *
     * @param phoneNumber
     * @return
     */
    @Override
    public ResponseResult send(String phoneNumber) {
        // 调用service-verification-code服务生成code
        ResponseResult responseResult = serviceVerificationCodeRestTemplateService.generateCode(IdentityConstant.PASSENGER, phoneNumber);
        VerifyCodeResponse verifyCodeResponse = null;
        if (responseResult.getCode() == CommonStatusEnum.SUCCESS.getCode()) {
            JSONObject jsonObject = JSONObject.parseObject((String) responseResult.getData());
            verifyCodeResponse = JSONObject.toJavaObject(jsonObject, VerifyCodeResponse.class);
        } else {
            return ResponseResult.fail("获取验证码失败");
        }
        String code = verifyCodeResponse.getCode();
        // 调用service-sms服务发送短信验证码
        ResponseResult result = serviceSmsRestTemplateService.sendSms(phoneNumber, code);
        if (result.getCode() != CommonStatusEnum.SUCCESS.getCode()) {
            return ResponseResult.fail("发送短信失败");
        }
        return ResponseResult.success(code);
    }
}