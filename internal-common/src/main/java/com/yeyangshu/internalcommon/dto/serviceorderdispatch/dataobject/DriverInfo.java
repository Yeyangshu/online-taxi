package com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 司机信息表
 * A data object class directly models database table <tt>tbl_driver_info</tt>.
 */
@Data
public class DriverInfo {
    /**
     *  field corresponds to the database column tbl_driver_info.id
     */
    private Integer id;

    /**
     *  field corresponds to the database column tbl_driver_info.phone_number
     */
    private String phoneNumber;

    /**
     *  field corresponds to the database column tbl_driver_info.driver_leader
     */
    private Integer driverLeader;

    /**
     * This field corresponds to the database column tbl_driver_info.driver_name
     */
    private String driverName;

    /**
     *  field corresponds to the database column tbl_driver_info.register_time
     */
    private Date registerTime;

    /**
     *  field corresponds to the database column tbl_driver_info.balance
     */
    private BigDecimal balance;

    /**
     *  field corresponds to the database column tbl_driver_info.gender
     */
    private Integer gender;

    /**
     *  field corresponds to the database column tbl_driver_info.car_id
     */
    private Integer carId;

    /**
     *  field corresponds to the database column tbl_driver_info.is_following
     */
    private Integer isFollowing;

    /**
     *  field corresponds to the database column tbl_driver_info.work_status
     */
    private Integer workStatus;

    /**
     *  field corresponds to the database column tbl_driver_info.head_img
     */
    private String headImg;

    /**
     *  field corresponds to the database column tbl_driver_info.city_code
     */
    private String cityCode;

    /**
     *  field corresponds to the database column tbl_driver_info.bind_time
     */
    private Date bindTime;

    /**
     *  field corresponds to the database column tbl_driver_info.use_status
     */
    private Integer useStatus;

    /**
     *  field corresponds to the database column tbl_driver_info.cs_work_status
     */
    private Integer csWorkStatus;

    /**
     *  field corresponds to the database column tbl_driver_info.sign_status
     */
    private Integer signStatus;

    /**
     *  field corresponds to the database column tbl_driver_info.tags
     */
    private String tags;

    /**
     *  field corresponds to the database column tbl_driver_info.create_time
     */
    private Date createTime;

    /**
     *  field corresponds to the database column tbl_driver_info.update_time
     */
    private Date updateTime;

}