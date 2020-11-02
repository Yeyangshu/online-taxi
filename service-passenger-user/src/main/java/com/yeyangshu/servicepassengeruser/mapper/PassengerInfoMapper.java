package com.yeyangshu.servicepassengeruser.mapper;

import com.yeyangshu.internalcommon.dto.servicepassengeruser.PassengerInfo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 13:55
 */
@Service
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