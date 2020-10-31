package com.yeyangshu.apipassenger.dao;

import com.yeyangshu.apipassenger.mapper.PassengerInfoMapper;
import com.yeyangshu.internalcommon.entity.PassengerInfo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 13:54
 */
@Repository
@RequiredArgsConstructor
public class PassengerInfoDao {

    @NonNull
    private PassengerInfoMapper passengerInfoMapper;

    public PassengerInfo queryPassengerInfoByPhoneNum(String phoneNum) {
        return passengerInfoMapper.queryPassengerInfoByPhoneNum(phoneNum);
    }
}
