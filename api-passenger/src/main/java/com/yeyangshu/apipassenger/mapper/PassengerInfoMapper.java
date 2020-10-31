package com.yeyangshu.apipassenger.mapper;

import com.yeyangshu.internalcommon.entity.PassengerInfo;

import java.util.List;
import java.util.Map;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 13:55
 */
public interface PassengerInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(PassengerInfo record);

    int insertSelective(PassengerInfo record);

    PassengerInfo selectByPrimaryKey(Integer id);

    List<PassengerInfo> selectByPrimaryKeyList();

    int updateByPrimaryKeySelective(PassengerInfo record);

    int updateByPrimaryKey(PassengerInfo record);

    PassengerInfo queryPassengerInfoByPhoneNum(String phoneNum);

    int updatePassengerBalance(Map<String, Object> map);

    void updatePassengerInfoLoginTime(Integer passengerId);

}
