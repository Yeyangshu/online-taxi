package com.yeyangshu.servicesms.dao;

import com.yeyangshu.servicesms.entity.ServiceSmsTemplate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信模板dao
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 22:52
 */
@Mapper
public interface ServiceSmsTemplateDao {

    int deleteByPrimaryKey(Integer id);

    int insert(ServiceSmsTemplate record);

    int insertSelective(ServiceSmsTemplate record);

    ServiceSmsTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceSmsTemplate record);

    int updateByPrimaryKey(ServiceSmsTemplate record);

}
