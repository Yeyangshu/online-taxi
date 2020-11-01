package com.yeyangshu.internalcommon.dto.servicepassengeruser;

import lombok.Data;

import java.util.Date;

/**
 * 乘客地址实体类
 * <tt>tbl_passenger_address</tt>
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 13:37
 */
@Data
public class PassengerAddress {

    /**
     * 数据库主键
     * This field corresponds to the database column tbl_passenger_address.id
     */
    private Integer id;

    /**
     * 乘客id
     * This field corresponds to the database column tbl_passenger_address.passenger_info_id
     */
    private Integer passengerInfoId;

    /**
     * 地址纬度
     * This field corresponds to the database column tbl_passenger_address.latitude
     */
    private Double latitude;

    /**
     * 地址纬度
     * This field corresponds to the database column tbl_passenger_address.latitude
     */
    private Double longitude;

    /**
     * 详细地址
     * This field corresponds to the database column tbl_passenger_address.address_name
     */
    private String addressName;

    /**
     * 地址类型，0:家，1：公司
     * This field corresponds to the database column tbl_passenger_address.type
     */
    private Integer type;

    /**
     * 地址描述
     * This field corresponds to the database column tbl_passenger_address.address_desc
     */
    private String addressDesc;

    /**
     * 创建时间
     * This field corresponds to the database column tbl_passenger_address.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * This field corresponds to the database column tbl_passenger_address.update_time
     */
    private Date updateTime;
}
