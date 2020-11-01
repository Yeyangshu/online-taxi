package com.yeyangshu.servicepassengeruser.controller;

import com.yeyangshu.internalcommon.constant.IdentityConstant;
import com.yeyangshu.internalcommon.constant.PhoneStatusCodeEnum;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.TokenRequest;
import com.yeyangshu.internalcommon.dto.servicepassengeruser.request.PhoneNumberRequest;
import com.yeyangshu.servicepassengeruser.service.PassengerInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户增删改查服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/16 7:37
 */
@RestController
@RequestMapping("/passenger")
@Slf4j
public class PassengerController {

    /**
     * Phone number length
     */
    private static final int PHONE_NUMBER_LENGTH = 11;

    @Autowired
    PassengerInfoService passengerInfoService;

    @PostMapping("/query")
    public ResponseResult query(@RequestBody @Validated TokenRequest request) {

        try {
            String phoneNumber = request.getPhoneNumber();
            if (StringUtils.isBlank(phoneNumber)) {
                return ResponseResult.fail(PhoneStatusCodeEnum.PHONE_NUM_EMPTY.getCode(), PhoneStatusCodeEnum.PHONE_NUM_EMPTY.getValue(), phoneNumber);
            }
            if (PHONE_NUMBER_LENGTH != phoneNumber.length()) {
                return ResponseResult.fail(PhoneStatusCodeEnum.PHONE_NUM_DIGIT.getCode(), PhoneStatusCodeEnum.PHONE_NUM_DIGIT.getValue(), phoneNumber);
            }
            return passengerInfoService.queryPassengerInfoByTokenRequest(request);
        } catch (Exception e) {
            log.error("操作异常", e);
            return ResponseResult.fail(1, "操作异常", request.getPhoneNumber());
        }
    }
}
