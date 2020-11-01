package com.yeyangshu.internalcommon.dto.apipassenger.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 预估价格信息接受类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:08
 */
@Data
public class EstimateRequest {

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单价格
     */
    private String orderPrice;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 服务类型id
     */
    private Integer serviceTypeId;

    /**
     * 服务类型名称
     */
    private String serviceTypeName;

    /**
     * 渠道名称id
     */
    private Integer channelId;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 车辆级别id
     */
    private Integer carLevelId;

    /**
     * 车辆级别
     */
    private String carLevelName;

    /**
     * 用车时长
     */
    private Integer useCarTime;

    /**
     * 过路费
     */
    private BigDecimal roadPrice;

    /**
     * 停车费
     */
    private BigDecimal parkingPrice;

    /**
     * 其它费用
     */
    private BigDecimal otherPrice;

    /**
     * This field corresponds to the database column tbl_order_reassignment_record.driver_id_now
     */
    private Integer driverIdNow;

    /**
     * 操作者
     * This field corresponds to the database column tbl_order_reassignment_record.operator
     */
    private String operator;

    /**
     * 改派类型
     * This field corresponds to the database column tbl_order_reassignment_record.reason_type
     */
    private Integer reasonType;

    /**
     * 改派内容
     * This field corresponds to the database column tbl_order_reassignment_record.reason_text
     */
    private String reasonText;

    /**
     * 订单ids
     */
    private String orderIds;

    /**
     * 更新类型
     */
    private Integer updateType;

    /**
     * 司机经度
     */
    private String driverLongitude;
    /**
     * 司机纬度
     */
    private String driverLatitude;
}
