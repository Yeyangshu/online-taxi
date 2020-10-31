package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.*;
import com.yeyangshu.internalcommon.constant.CommonStatusEnum;
import com.yeyangshu.internalcommon.constant.IdentityConstant;
import com.yeyangshu.internalcommon.constant.IdentityEnum;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.TokenRequest;
import com.yeyangshu.internalcommon.dto.apipassenger.response.PassengerRegisterResponse;
import com.yeyangshu.internalcommon.entity.PassengerInfo;
import com.yeyangshu.internalcommon.entity.PassengerRegisterSource;
import com.yeyangshu.internalcommon.util.EncryptUtil;
import com.yeyangshu.internalcommon.util.JwtUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        // 验证短信验证码
        ResponseResult responseResult = serviceVerificationCodeRestTemplateService
                .verifyCode(IdentityConstant.PASSENGER, request.getPhoneNumber(), request.getVerifyCode());
        if (responseResult.getCode() != CommonStatusEnum.SUCCESS.getCode()) {
            return ResponseResult.fail("登录失败：验证码校验失败");
        }
        log.info("passenger start sign in");
        String phoneNumber = request.getPhoneNumber();
        String encryptPhoneNumber = EncryptUtil.toHexString(EncryptUtil.encrypt(phoneNumber));
        log.info("乘客加密后的手机号：" + encryptPhoneNumber);
        PassengerInfo passengerInfo = passengerInfoService.queryPassengerInfoByPhoneNum(encryptPhoneNumber);
        log.info("根据手机号查询，乘客信息。" + passengerInfo);
        PassengerInfo passengerInfoTemp = new PassengerInfo();
        int passengerId;
        int isNewPassenger = OLD_PASSENGER;
        // 若查询不到乘客信息，记录乘客信息
        if (null == passengerInfo) {
            isNewPassenger = NEW_PASSENGER;
            Date date = new Date();
            passengerInfoTemp.setLastLoginTime(date);
            passengerInfoTemp.setLastLoginMethod(METHOD);
            passengerInfoTemp.setPhone(encryptPhoneNumber);
            passengerInfoTemp.setBalance(new BigDecimal(0));
            passengerInfoTemp.setRegisterTime(date);
            passengerInfoTemp.setCreateTime(date);
            passengerInfoTemp.setUpdateTime(date);
            log.info("新增乘客手机号：" + encryptPhoneNumber);
            passengerInfoService.insertPassengerInfo(passengerInfoTemp);
            passengerId = passengerInfoTemp.getId();
            // 新增注册来源
            try {
                PassengerRegisterSource passengerRegisterSource = new PassengerRegisterSource();
                passengerRegisterSource.setPassengerInfoId(passengerId);
                String registerSource = request.getRegisterSource();
                passengerRegisterSource.setRegisterSource(registerSource);
                passengerRegisterSource.setMarketChannel(request.getMarketChannel());
                passengerRegisterSource.setCreateTime(new Date());
                passengerInfoService.insertPassengerRegisterSource(passengerRegisterSource);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("乘客注册或登录 - " + encryptPhoneNumber + " - 校验注册状态 - 用户未注册，已插入新用户记录");
            // 初始化乘客钱包
            passengerInfoService.initPassengerWallet(passengerId);
        } else {
            log.info("乘客注册或登录 - " + encryptPhoneNumber + " - 校验注册状态 - 用户已注册");
            // 若乘客登录或者注册过了，更新登录时间
            passengerId = passengerInfo.getId();
            passengerInfoService.updatePassengerInfoLoginTime(passengerId);
        }
        // 乘客登录，生成jwtStr
        String subject = IdentityEnum.PASSENGER.getCode() + "_" + phoneNumber + "_" + passengerId;
        log.info("token subject:" + subject);
        String jwtStr = JwtUtil.createJavaWebToken(subject, new Date());
        redisTokenService.put(subject, jwtStr, EXP_HOURS);
        log.info("乘客注册或登录用户-" + phoneNumber + "- access_token:" + jwtStr);
        // 多终端互踢
        return createResponse(jwtStr, passengerInfo.getPassengerName(), passengerInfo.getGender(),
                passengerInfo.getBalance(), phoneNumber, passengerInfo.getHeadImg(), passengerId,
                passengerInfo.getLastLoginTime(), passengerInfo.getLastLoginMethod(), passengerInfo.getContact(),
                passengerInfo.getShare(), passengerInfo.getSharingTime(), passengerInfo.getBirthday() == null ? null : passengerInfo.getBirthday().getTime(),
                isNewPassenger);
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