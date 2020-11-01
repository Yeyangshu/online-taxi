package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.*;
import com.yeyangshu.internalcommon.constant.CommonStatusEnum;
import com.yeyangshu.internalcommon.constant.IdentityConstant;
import com.yeyangshu.internalcommon.constant.IdentityEnum;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.TokenRequest;
import com.yeyangshu.internalcommon.dto.apipassenger.response.PassengerRegisterResponse;
import com.yeyangshu.internalcommon.dto.servicepassengeruser.PassengerInfo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 登录验证实现类
 *
 * @author yeyangshu
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ServiceVerificationCodeRestTemplateService serviceVerificationCodeRestTemplateService;

    @Autowired
    private ServicePassengerUserService servicePassengerUserService;

    private final RedisTokenService redisTokenService;

    @NonNull
    PassengerInfoService passengerInfoService;

    /**
     * 新用户
     */
    private static final int NEW_PASSENGER = 0;

    /**
     * 老用户
     */
    private static final int OLD_PASSENGER = 1;

    /**
     * 验证码登录方式
     */
    private static final byte METHOD = 1;

    /**
     * token有效期 1天
     */
    private static final Integer EXP_HOURS = 24;

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult auth(TokenRequest request) {
        // 调用短信验证服务，验证短信验证码
        log.info("invoke verification code service, verify code");
        ResponseResult responseResult = serviceVerificationCodeRestTemplateService
                .verifyCode(IdentityConstant.PASSENGER, request.getPhoneNumber(), request.getVerifyCode());
        if (responseResult.getCode() != CommonStatusEnum.SUCCESS.getCode()) {
            return ResponseResult.fail("登录失败：验证码校验失败");
        }
        log.info("passenger start sign in");
        // 调用乘客服务，查询乘客信息
        ResponseResult queryPassengerInfo = servicePassengerUserService.queryPassenger(request);
        log.info("invoke passenger users service, query passenger info");

        JSONObject jsonObject = JSONObject.fromObject(queryPassengerInfo.getData());
        String jwtStr = (String) jsonObject.get("token");
        PassengerInfo passengerInfo = (PassengerInfo) JSONObject.toBean(JSONObject.fromObject(
                jsonObject.get("passengerInfo")), PassengerInfo.class);
        int isNewPassenger = Integer.parseInt(jsonObject.get("isNewPassenger").toString());

        return createResponse(jwtStr, passengerInfo.getPassengerName(), passengerInfo.getGender(),
                passengerInfo.getBalance(), passengerInfo.getPhone(), passengerInfo.getHeadImg(), passengerInfo.getId(),
                passengerInfo.getLastLoginTime(), passengerInfo.getLastLoginMethod(), passengerInfo.getContact(),
                passengerInfo.getShare(), passengerInfo.getSharingTime(),
                passengerInfo.getBirthday() == null ? null : passengerInfo.getBirthday().getTime(), isNewPassenger);
    }

    /**
     * 封装响应协议
     *
     * @param accessToken   TOKEN
     * @param passengerName 乘客姓名
     * @param sex           性别
     * @param balance       账户
     * @param phoneNum      手机号
     * @param headImg       头像
     * @param id            id
     * @param lastLoginTime 登录时间
     * @param method        登录方式
     * @return ResponseResult实例
     */
    private ResponseResult createResponse(String accessToken, String passengerName, Byte sex, BigDecimal balance, String phoneNum, String headImg,
                                          Integer id, Date lastLoginTime, Byte method, Integer isContact, Integer isShare, String sharingTime, Long birthday,
                                          Integer isNewer) {
        PassengerRegisterResponse response = new PassengerRegisterResponse();
        response.setStatus("0");
        response.setAccessToken(accessToken);
        response.setPassengerName(StringUtils.isBlank(passengerName) ? "" : passengerName);
        response.setGender(sex == null ? 0 : sex);
        response.setId(id);
        response.setBalance(balance);
        response.setPhoneNum(phoneNum);
        response.setHeadImg(StringUtils.isBlank(headImg) ? "" : headImg);
        //极光ID
        String jPushId = IdentityEnum.PASSENGER.getCode() + "_" + phoneNum + "_" + Calendar.getInstance().getTimeInMillis();
        response.setJpushId(jPushId);
        response.setLastLoginTime(lastLoginTime);
        response.setLastLoginMethod(method);
        response.setContact(isContact);
        response.setShare(isShare);
        response.setSharingTime(sharingTime);
        response.setBirthday(birthday);
        response.setNewer(isNewer);
        return ResponseResult.success(response);
    }
}