package com.yeyangshu.servicesms.dao;

import com.yeyangshu.servicesms.entity.ServiceSmsTemplate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信模板dao
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 22:44
 */
@Mapper
public interface ServiceSmsTemplateCustomDao extends ServiceSmsTemplateDao {

    /**
     * 根据短信模板id查找短信模板
     * @param templateId
     * @return
     */
    ServiceSmsTemplate selectByTemplateId(String templateId);
}
