package com.yeyangshu.servicepassengeruser.controller;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.servicepassengeruser.request.LoginRequest;
import com.yeyangshu.servicepassengeruser.service.PassengerUserService;
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
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PassengerUserService passengerUserService;

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody @Validated LoginRequest request) {
        String passengerPhone = request.getPassengerPhone();
        return passengerUserService.login(passengerPhone);
    }
}