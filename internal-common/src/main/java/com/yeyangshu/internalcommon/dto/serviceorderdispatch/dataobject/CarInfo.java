package com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 车辆信息
 * A data object class directly models database table <tt>tbl_car_info</tt>.
 */
@Data
public class CarInfo {

    /**
     * This field corresponds to the database column tbl_car_info.id
     */
    private Integer id;

    /**
     * This field corresponds to the database column tbl_car_info.plate_number
     */
    private String plateNumber;

    /**
     * This field corresponds to the database column tbl_car_info.operation_status
     */
    private Integer operationStatus;

    /**
     * This field corresponds to the database column tbl_car_info.publish_time
     */
    private Date publishTime;

    /**
     * This field corresponds to the database column tbl_car_info.full_name
     */
    private String fullName;

    /**
     * This field corresponds to the database column tbl_car_info.color
     */
    private String color;

    /**
     * This field corresponds to the database column tbl_car_info.car_img
     */
    private String carImg;

    /**
     * This field corresponds to the database column tbl_car_info.city_code
     */
    private String cityCode;

    /**
     * This field corresponds to the database column tbl_car_info.car_type_id
     */
    private Integer carTypeId;

    /**
     * This field corresponds to the database column tbl_car_info.car_level_id
     */
    private Integer carLevelId;

    /**
     * This field corresponds to the database column tbl_car_info.regist_date
     */
    private Date registDate;

    /**
     * This field corresponds to the database column tbl_car_info.insurance_start_date
     */
    private Date insuranceStartDate;

    /**
     * This field corresponds to the database column tbl_car_info.insurance_end_date
     */
    private Date insuranceEndDate;

    /**
     * This field corresponds to the database column tbl_car_info.annual_end_date
     */
    private Date annualEndDate;

    /**
     * This field corresponds to the database column tbl_car_info.car_license_img
     */
    private String carLicenseImg;

    /**
     * This field corresponds to the database column tbl_car_info.is_free_order
     */
    private Integer isFreeOrder;

    /**
     * This field corresponds to the database column tbl_car_info.remark
     */
    private String remark;

    /**
     * This field corresponds to the database column tbl_car_info.use_status
     */
    private Integer useStatus;

    /**
     * This field corresponds to the database column tbl_car_info.large_screen_device_code
     */
    private String largeScreenDeviceCode;

    /**
     * This field corresponds to the database column tbl_car_info.large_screen_device_brand
     */
    private String largeScreenDeviceBrand;

    /**
     * This field corresponds to the database column tbl_car_info.car_screen_device_code
     */
    private String carScreenDeviceCode;

    /**
     * This field corresponds to the database column tbl_car_info.car_screen_device_brand
     */
    private String carScreenDeviceBrand;

    /**
     * This field corresponds to the database column tbl_car_info.operator_id
     */
    private Integer operatorId;

    /**
     * This field corresponds to the database column tbl_car_info.total_mile
     */
    private Integer totalMile;

    /**
     * This field corresponds to the database column tbl_car_info.asset_coding
     */
    private String assetCoding;

    /**
     * This field corresponds the database column tbl_car_info.create_time
     */
    private Date createTime;

    /**
     * This field corresponds the database column tbl_car_info.update_time
     */
    private Date updateTime;

}