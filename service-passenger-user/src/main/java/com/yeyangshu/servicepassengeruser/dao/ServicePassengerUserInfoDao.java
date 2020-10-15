package com.yeyangshu.servicepassengeruser.dao;

import com.yeyangshu.servicepassengeruser.entity.ServicePassengerUserInfoDo;

/**
 * A dao interface provides methods to access database table <tt>service_passenger_user_info</tt>.
 *
 * @author yeyangshu
 */
public interface ServicePassengerUserInfoDao {

    int deleteByPrimaryKey(Long id);

    int insert(ServicePassengerUserInfoDo record);

    int insertSelective(ServicePassengerUserInfoDo record);

    ServicePassengerUserInfoDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ServicePassengerUserInfoDo record);

    int updateByPrimaryKey(ServicePassengerUserInfoDo record);

}