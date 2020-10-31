package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.internalcommon.dto.apipassenger.request.TokenRequest;
import com.yeyangshu.apipassenger.service.PassengerInfoService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.entity.PassengerInfo;
import com.yeyangshu.internalcommon.entity.PassengerRegisterSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 乘客信息服务实现类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 13:53
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PassengerInfoServiceImpl implements PassengerInfoService {

    @Override
    public PassengerInfo queryPassengerInfoByPhoneNum(String phoneNum) {
        return null;
    }

    @Override
    public void insertPassengerInfo(PassengerInfo passengerInfo) {

    }

    @Override
    public void updatePassengerInfoLoginTime(Integer passengerId) {

    }

    @Override
    public HashMap<String, Object> getPassengerInfoView(TokenRequest tokenRequest) {
        return null;
    }

    @Override
    public ResponseResult updatePassengerInfo(PassengerInfo passengerInfo) {
        return null;
    }

    @Override
    public int initPassengerWallet(Integer passengerId) {
        return 0;
    }

    @Override
    public PassengerInfo queryPassengerInfoById(Integer passengerId) {
        return null;
    }

    @Override
    public int insertPassengerRegisterSource(PassengerRegisterSource passengerRegisterSource) {
        return 0;
    }

    @Override
    public int updatePassengerInfoById(PassengerInfo passengerInfo) {
        return 0;
    }
}
