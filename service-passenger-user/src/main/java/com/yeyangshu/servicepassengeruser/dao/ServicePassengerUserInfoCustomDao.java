package com.yeyangshu.servicepassengeruser.dao;

import com.yeyangshu.servicepassengeruser.entity.ServicePassengerUserInfoDo;

public interface ServicePassengerUserInfoCustomDao extends ServicePassengerUserInfoDao {

    /**
     * 根据手机号查询乘客信息
     *
     * @param passengerPhone
     * @return
     */
    ServicePassengerUserInfoDo selectByPhoneNumber(String passengerPhone);
}