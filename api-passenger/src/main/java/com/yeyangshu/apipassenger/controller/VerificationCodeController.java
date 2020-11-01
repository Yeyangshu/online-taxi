package com.yeyangshu.apipassenger.controller;

import com.yeyangshu.apipassenger.service.VerificationCodeService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.servicepassengeruser.request.PhoneNumberRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户发送验证码
 *
 * @author yeyangshu
 */
@RestController
@RequestMapping("/verify-code")
public class VerificationCodeController {

    @Autowired
    VerificationCodeService verificationCodeService;

    @PostMapping("/send")
    public ResponseResult send(@RequestBody @Validated PhoneNumberRequest request) {
        return verificationCodeService.send(request.getPhoneNumber());
    }

}