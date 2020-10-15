package com.yeyangshu.servicesms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信模板数据库表<tt>service_sms_template</tt>
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 22:45
 */
@Data
public class ServiceSmsTemplate implements Serializable {

    private static final long serialVersionUID = 8524675768347308280L;

    /**
     * 数据库主键id
     */
    private int id;

    /**
     * 短信模板id
     */
    private String templateId;

    /**
     * 短信模板名
     */
    private String templateName;

    /**
     * 短信模板内容
     */
    private String templateContent;

    /**
     * 模板类型（1：营销；2：通知；3：订单）
     */
    private String templateType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}