package com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 车辆调度运力设置
 * A data object class directly models database table <tt>tbl_car_dispatch_capacity_set</tt>.
 */
@Data
public class CarDispatchCapacitySet {

    /**
     * 数据库自增主键
     * This field corresponds to the database column tbl_car_dispatch_capacity_set.id
     */
    private Integer id;

    /**
     * 城市编码
     * This field corresponds to the database column tbl_car_dispatch_capacity_set.city_code
     */
    private String cityCode;

    /**
     * 车辆服务时段，1：白天，2：夜晚
     * This field corresponds to the database column tbl_car_dispatch_capacity_set.car_service_period
     */
    private String carServicePeriod;

    /**
     * 空闲司机数量
     * This field corresponds to the database column tbl_car_dispatch_capacity_set.spare_driver_count
     */
    private Integer spareDriverCount;

    /**
     * 操作人id
     * This field corresponds to the database column tbl_car_dispatch_capacity_set.operator_id
     */
    private Integer operatorId;

    /**
     * 创建时间
     * This field corresponds to the database column tbl_car_dispatch_capacity_set.create_time
     */
    private Date createTime;

    /**
     * 修改时间
     * This field corresponds to the database column tbl_car_dispatch_capacity_set.update_time
     */
    private Date updateTime;
}