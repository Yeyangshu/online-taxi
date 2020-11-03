package com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 乘客登录返回消息类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 14:55
 */
@Data
public class PassengerRegisterResponse {

    /**
     * 状态码：0:成功 1004:验证码验证错误，3401：一小时内验证码错误达3次，请十分钟后登录
     */
    private String status;

    /**
     * token
     */
    private String accessToken ;

    /**
     * 手机号
     */
    private String phoneNum;

    /**
     * 性别
     */
    private Byte gender;

    /**
     *
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String passengerName;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 头像url
     */
    private String headImg;

    /**
     * 极光注册Id
     */
    private String jpushId;

    /**
     * 最后登录方式
     */
    private int lastLoginMethod;

    /**
     * 最后登录时间
     */
    private Date LastLoginTime;

    /**
     *
     */
    private Integer contact;

    /**
     *
     */
    private Integer share;

    /**
     *
     */
    private String sharingTime;

    /**
     * 生日
     */
    private Long birthday;

    /**
     * 是否是新用户
     */
    private Integer newer;
}
