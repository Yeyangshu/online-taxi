package com.yeyangshu.servicesms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信记录数据库表<tt>service_sms_record</tt>
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/13 22:55
 */
@Data
public class ServiceSmsRecord implements Serializable {

    private static final long serialVersionUID = -1335584260602208742L;

    /**
     * 数据记录id
     */
    private Integer id;

    /**
     * 乘客手机号
     */
    private String phoneNumber;

    /**
     * 短信内容
     */
    private String smsContent;

    /**
     * 短信发送时间
     */
    private Date sendTime;

    /**
     * 操作人
     */
    private String operatorName;

    /**
     * 发送状态 0:失败  1: 成功
     */
    private Integer sendFlag;

    /**
     * 发送失败次数
     */
    private Integer sendNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
