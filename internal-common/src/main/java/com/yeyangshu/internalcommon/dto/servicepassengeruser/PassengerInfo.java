package com.yeyangshu.internalcommon.dto.servicepassengeruser;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 乘客信息实体类
 * <tt>tbl_passenger_info</tt>
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 13:37
 */
@Data
public class PassengerInfo {

    /**
     * 数据库主键
     * This field corresponds to the database column tbl_passenger_info.id
     */
    private Integer id;

    /**
     * 用户手机号
     * This field corresponds to the database column tbl_passenger_info.phone
     */
    private String phone;

    /**
     * 学历
     * This field corresponds to the database column tbl_passenger_info.educatioan
     */
    private String education;

    /**
     * 生日
     * This field corresponds to the database column tbl_passenger_info.birthday
     */
    private Date birthday;

    /**
     * 乘客名称
     * This field corresponds to the database column tbl_passenger_info.passenger_name
     */
    private String passengerName;

    /**
     * 注册时间
     * This field corresponds to the database column tbl_passenger_info.register_time
     */
    private Date registerTime;

    /**
     * 乘客余额
     * This field corresponds to the database column tbl_passenger_info.balance
     */
    private BigDecimal balance;

    /**
     * 乘客性别，0：女，1：男
     * This field corresponds to the database column tbl_passenger_info.gender
     */
    private Byte gender;

    /**
     * 乘客头像
     * This field corresponds to the database column tbl_passenger_info.head_img
     */
    private String headImg;

    /**
     * 用户类型，1：个人用户，2：企业用户
     * This field corresponds to the database column tbl_passenger_info.passenger_type
     */
    private Byte passengerType;

    /**
     * 会员等级
     * This field corresponds to the database column tbl_passenger_info.user_level
     */
    private Byte userLevel;

    /**
     * 注册渠道 1 安卓 2 ios
     * This field corresponds to the database column tbl_passenger_info.register_type
     */
    private Short registerType;

    /**
     * 最后一次登录时间
     * This field corresponds to the database column tbl_passenger_info.last_login_time
     */
    private Date lastLoginTime;

    /**
     * 上次登陆方式：1：验证码，2：密码
     * This field corresponds to the database column tbl_passenger_info.last_login_method
     */
    private Byte lastLoginMethod;

    /**
     * 上次登录大屏时间
     * This field corresponds to the database column tbl_passenger_info.last_login_screen_time
     */
    private Date lastLoginScreenTime;

    /**
     * 上次登录大屏方式
     * This field corresponds to the database column tbl_passenger_info.last_login_screen_method
     */
    private String lastLoginScreenMethod;

    /**
     * 最后一次下单时间
     * This field corresponds to the database column tbl_passenger_info.last_order_time
     */
    private Date lastOrderTime;

    /**
     * 创建时间
     * This field corresponds to the database column tbl_passenger_info.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * This field corresponds to the database column tbl_passenger_info.update_time
     */
    private Date updateTime;

    private Integer contact;

    private Integer share;

    private String sharingTime;
}
