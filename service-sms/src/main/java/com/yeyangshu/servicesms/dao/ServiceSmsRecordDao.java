package com.yeyangshu.servicesms.dao;

import com.yeyangshu.servicesms.entity.ServiceSmsRecord;

/**
 * 短信记录dao
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 22:54
 */
public interface ServiceSmsRecordDao {

    int deleteByPrimaryKey(Integer id);

    int insert(ServiceSmsRecord record);

    int insertSelective(ServiceSmsRecord record);

    ServiceSmsRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceSmsRecord record);

    int updateByPrimaryKey(ServiceSmsRecord record);

}
