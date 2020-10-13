package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.VerificationCodeService;
import com.yeyangshu.internalcommon.constant.CommonStatusEnum;
import com.yeyangshu.internalcommon.constant.IdentityConstant;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.serviceverificationcode.response.VerifyCodeResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    ServiceVerificationCodeRestTemplateServiceImpl serviceVerificationCodeRestTemplateService;

    @Override
    public ResponseResult send(String phoneNumber) {
        ResponseResult responseResult = serviceVerificationCodeRestTemplateService.generateCode(IdentityConstant.PASSENGER, phoneNumber);
        VerifyCodeResponse verifyCodeResponse = null;
        if (responseResult.getCode() == CommonStatusEnum.SUCCESS.getCode()) {
            JSONObject data = JSONObject.fromObject(responseResult.getData().toString());
            verifyCodeResponse = (VerifyCodeResponse) JSONObject.toBean(data,VerifyCodeResponse.class);
        } else {
            return ResponseResult.fail("获取验证码失败");
        }

        String code = verifyCodeResponse.getCode();
        log.info("code=" + code);
        //调用第三方短信
        return ResponseResult.success("");
    }
}