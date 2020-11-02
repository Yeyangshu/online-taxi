package com.yeyangshu.serviceverificationcode.controller;

import com.yeyangshu.serviceverificationcode.service.VerifyCodeService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.serviceverificationcode.request.VerifyCodeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 验证码
 *
 * @author yeyangshu
 */
@RestController
@RequestMapping("/verify-code")
@Slf4j
public class VerifyCodeController {

    @Autowired
    VerifyCodeService verifyCodeService;

    @GetMapping("/generate/{identity}/{phoneNumber}")
    public ResponseResult generate(@PathVariable("identity") int identity, @PathVariable("phoneNumber") String phoneNumber) {
        return verifyCodeService.generateCode(identity, phoneNumber);
    }

    @PostMapping("/verify")
    public ResponseResult verifyCode(@RequestBody VerifyCodeRequest request) {
        //获取身份、手机号和验证码
        String phoneNumber = request.getPhoneNumber();
        int identity = request.getIdentity();
        String code = request.getCode();
        return verifyCodeService.verifyCode(identity, phoneNumber, code);
    }
}