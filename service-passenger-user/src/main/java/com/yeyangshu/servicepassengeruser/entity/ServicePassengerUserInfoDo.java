package com.yeyangshu.servicepassengeruser.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * A data object class directly models database table <tt>service_passenger_user_info</tt>.
 *
 * @author yeyangshu
 */
@Data
public class ServicePassengerUserInfoDo implements Serializable {

    private static final long serialVersionUID = 6046897618455642857L;

    /**
     * 数据库主键
     */
    private Long id;

    /**
     * 注册日期
     */
    private Date registerDate;

    /**
     * 乘客手机号
     */
    private String passengerPhone;

    /**
     * 乘客姓名
     */
    private String passengerName;

    /**
     * 性别。1：男，0：女
     */
    private Byte passengerGender;

    /**
     * 用户状态：1：有效，0：失效
     */
    private Byte userState;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;

}