package com.yeyangshu.apipassenger.controller;

import com.yeyangshu.apipassenger.service.AuthService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.UserAuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/16 7:37
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody @Validated UserAuthRequest request) {
        String passengerPhone = request.getPassengerPhone();
        String code = request.getCode();
        return authService.auth(passengerPhone, code);
    }
}
