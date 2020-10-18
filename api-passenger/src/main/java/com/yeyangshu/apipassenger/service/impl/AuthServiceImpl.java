package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.AuthService;
import com.yeyangshu.apipassenger.service.ServicePassengerUserService;
import com.yeyangshu.apipassenger.service.ServiceVerificationCodeRestTemplateService;
import com.yeyangshu.internalcommon.constant.CommonStatusEnum;
import com.yeyangshu.internalcommon.constant.IdentityConstant;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录验证实现类
 *
 * @author yeyangshu
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ServiceVerificationCodeRestTemplateService serviceVerificationCodeRestTemplateService;

    @Autowired
    private ServicePassengerUserService servicePassengerUserService;

    /**
     * 用户登录
     *
     * @param passengerPhone
     * @param code
     * @return
     */
    @Override
    public ResponseResult auth(String passengerPhone, String code) {
        // 验证短信验证码
        ResponseResult responseResult = serviceVerificationCodeRestTemplateService.verifyCode(IdentityConstant.PASSENGER, passengerPhone, code);
        if (responseResult.getCode() != CommonStatusEnum.SUCCESS.getCode()) {
            return ResponseResult.fail("登录失败：验证码校验失败");
        }
        // 用户登录


        return ResponseResult.success("");
    }
}