package com.yeyangshu.internalcommon.dto.serviceorder;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * A data object class directly models database table <tt>tbl_order_rule_price</tt>.
 */
@Data
public class OrderRulePrice {

    /**
     * This field corresponds to the database column tbl_order_rule_price.id
     */
    private Integer id;

    /**
     * This field corresponds to the database column tbl_order_rule_price.order_id
     */
    private Integer orderId;

    /**
     * This field corresponds to the database column tbl_order_rule_price.category
     */
    private String category;

    /**
     * This field corresponds to the database column tbl_order_rule_price.total_price
     */
    private BigDecimal totalPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.total_distance
     */
    private Double totalDistance;

    /**
     * This field corresponds to the database column tbl_order_rule_price.total_time
     */
    private Double totalTime;

    /**
     * This field corresponds to the database column tbl_order_rule_price.city_code
     */
    private String cityCode;

    /**
     * This field corresponds to the database column tbl_order_rule_price.city_name
     */
    private String cityName;

    /**
     * This field corresponds to the database column tbl_order_rule_price.service_type_id
     */
    private Integer serviceTypeId;

    /**
     * This field corresponds to the database column tbl_order_rule_price.service_type_name
     */
    private String serviceTypeName;

    /**
     * This field corresponds to the database column tbl_order_rule_price.channel_id
     */
    private Integer channelId;

    /**
     * This field corresponds to the database column tbl_order_rule_price.channel_name
     */
    private String channelName;

    /**
     * This field corresponds to the database column tbl_order_rule_price.car_level_id
     */
    private Integer carLevelId;

    /**
     * This field corresponds to the database column tbl_order_rule_price.car_level_name
     */
    private String carLevelName;

    /**
     * This field corresponds to the database column tbl_order_rule_price.base_price
     */
    private BigDecimal basePrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.base_kilo
     */
    private Double baseKilo;

    /**
     * This field corresponds to the database column tbl_order_rule_price.base_minute
     */
    private Double baseMinute;

    /**
     * This field corresponds to the database column tbl_order_rule_price.lowest_price
     */
    private BigDecimal lowestPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.night_start
     */
    private Date nightStart;

    /**
     * This field corresponds to the database column tbl_order_rule_price.night_end
     */
    private Date nightEnd;

    /**
     * This field corresponds to the database column tbl_order_rule_price.night_per_kilo_price
     */
    private BigDecimal nightPerKiloPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.night_per_minute_price
     */
    private BigDecimal nightPerMinutePrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.night_distance
     */
    private Double nightDistance;

    /**
     * This field corresponds to the database column tbl_order_rule_price.night_time
     */
    private Double nightTime;

    /**
     * This field corresponds to the database column tbl_order_rule_price.night_price
     */
    private BigDecimal nightPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.beyond_start_kilo
     */
    private Double beyondStartKilo;

    /**
     * This field corresponds to the database column tbl_order_rule_price.beyond_per_kilo_price
     */
    private BigDecimal beyondPerKiloPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.beyond_distance
     */
    private Double beyondDistance;

    /**
     * This field corresponds to the database column tbl_order_rule_price.beyond_price
     */
    private BigDecimal beyondPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.per_kilo_price
     */
    private BigDecimal perKiloPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.path
     */
    private Double path;

    /**
     * This field corresponds to the database column tbl_order_rule_price.path_price
     */
    private BigDecimal pathPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.per_minute_price
     */
    private BigDecimal perMinutePrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.duration
     */
    private Double duration;

    /**
     * This field corresponds to the database column tbl_order_rule_price.duration_price
     */
    private BigDecimal durationPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.rest_duration
     */
    private Double restDuration;

    /**
     * This field corresponds to the database column tbl_order_rule_price.rest_duration_price
     */
    private BigDecimal restDurationPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.rest_distance
     */
    private Double restDistance;

    /**
     * This field corresponds to the database column tbl_order_rule_price.rest_distance_price
     */
    private BigDecimal restDistancePrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.road_price
     */
    private BigDecimal roadPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.parking_price
     */
    private BigDecimal parkingPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.other_price
     */
    private BigDecimal otherPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.dynamic_discount_rate
     */
    private Double dynamicDiscountRate;

    /**
     * This field corresponds to the database column tbl_order_rule_price.dynamic_discount
     */
    private BigDecimal dynamicDiscount;

    /**
     * This field corresponds to the database column tbl_order_rule_price.supplement_price
     */
    private BigDecimal supplementPrice;

    /**
     * This field corresponds to the database column tbl_order_rule_price.create_time
     */
    private Date createTime;

    /**
     * This field corresponds to the database column tbl_order_rule_price.update_time
     */
    private Date updateTime;
}