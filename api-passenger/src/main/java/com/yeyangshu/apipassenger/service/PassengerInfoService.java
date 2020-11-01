package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.dto.apipassenger.request.TokenRequest;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.servicepassengeruser.PassengerInfo;
import com.yeyangshu.internalcommon.dto.servicepassengeruser.PassengerRegisterSource;

import java.util.HashMap;

/**
 * 乘客信息接口
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 11:33
 */
public interface PassengerInfoService {

    /**
     * 通过手机号查询乘客信息
     *
     * @param phoneNum 乘客手机号
     * @return 乘客信息
     */
    PassengerInfo queryPassengerInfoByPhoneNum(String phoneNum);

    void insertPassengerInfo(PassengerInfo passengerInfo);

    void updatePassengerInfoLoginTime(Integer passengerId);

    HashMap<String, Object> getPassengerInfoView(TokenRequest tokenRequest);

    ResponseResult updatePassengerInfo(PassengerInfo passengerInfo);

    int initPassengerWallet(Integer passengerId);

    PassengerInfo queryPassengerInfoById(Integer passengerId);

    int insertPassengerRegisterSource(PassengerRegisterSource passengerRegisterSource);

    int updatePassengerInfoById(PassengerInfo passengerInfo);
}

