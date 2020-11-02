package com.yeyangshu.apipassenger.controller;

import com.yeyangshu.apipassenger.constant.AccountStatusCode;
import com.yeyangshu.apipassenger.service.AuthService;
import com.yeyangshu.internalcommon.constant.IdentityConstant;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.TokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

/**
 * 用户登录
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/16 7:37
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    /**
     * Phone number length
     */
    private static final int PHONE_NUMBER_LENGTH = 11;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody @Validated TokenRequest request) {

        try {
            String phoneNumber = request.getPhoneNumber();
            if (StringUtils.isBlank(phoneNumber)) {
                return ResponseResult.fail(AccountStatusCode.PHONE_NUM_EMPTY.getCode(), AccountStatusCode.PHONE_NUM_EMPTY.getValue(), phoneNumber);
            }
            if (PHONE_NUMBER_LENGTH != phoneNumber.length()) {
                return ResponseResult.fail(AccountStatusCode.PHONE_NUM_DIGIT.getCode(), AccountStatusCode.PHONE_NUM_DIGIT.getValue(), phoneNumber);
            }
            if (!Pattern.compile(AccountStatusCode.PHONE_NUMBER_VERIFICATION.getValue()).matcher(phoneNumber).matches()) {
                return ResponseResult.fail(AccountStatusCode.PHONE_NUM_ERROR.getCode(), AccountStatusCode.PHONE_NUM_ERROR.getValue(), phoneNumber);
            }
            request.setIdentityStatus(IdentityConstant.PASSENGER);
            return authService.auth(request);
        } catch (Exception e) {
            log.error("登录操作异常", e);
            return ResponseResult.fail(1, "登录操作异常", request.getPhoneNumber());
        }
    }
}
