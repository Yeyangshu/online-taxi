package com.yeyangshu.internalcommon.entity.serviceorderdispatch.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 车辆调度顺风单半径设置
 * A data object class directly models database table <tt>tbl_car_dispatch_direct_route_order_radius_set</tt>.
 */
@Data
public class CarDispatchDirectRouteOrderRadiusSet {

    /**
     * 数据库自增主键
     * This field corresponds to the database column tbl_car_dispatch_direct_route_order_radius_set.id
     */
    private Integer id;

    /**
     * 城市编码
     * This field corresponds to the database column tbl_car_dispatch_direct_route_order_radius_set.city_code
     */
    private String cityCode;

    /**
     * 服务类型id
     * This field corresponds to the database column tbl_car_dispatch_direct_route_order_radius_set.service_type_id
     */
    private Integer serviceTypeId;

    /**
     * 顺风单类型，1：回家单，2：接力单，3：特殊时段预约单
     * This field corresponds to the database column tbl_car_dispatch_direct_route_order_radius_set.direct_route_order_type
     */
    private Boolean directRouteOrderType;

    /**
     * 顺风单半径（公里）
     * This field corresponds to the database column tbl_car_dispatch_direct_route_order_radius_set.direct_route_order_radius
     */
    private Integer directRouteOrderRadius;

    /**
     * 是否删除，0：未删除，1：已删除
     * This field corresponds to the database column tbl_car_dispatch_direct_route_order_radius_set.is_delete
     */
    private Boolean delete;

    /**
     * 操作人id
     * This field corresponds to the database column tbl_car_dispatch_direct_route_order_radius_set.operator_id
     */
    private Integer operatorId;

    /**
     * 创建时间
     * This field corresponds to the database column tbl_car_dispatch_direct_route_order_radius_set.create_time
     */
    private Date createTime;

    /**
     * 修改时间
     * This field corresponds to the database column tbl_car_dispatch_direct_route_order_radius_set.update_time
     */
    private Date updateTime;
}