package com.yeyangshu.internalcommon.dto.servicevaluation;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ChargeRule {

    /**
     * 数据库主键
     * This field corresponds to the database column tbl_charge_rule.id
     */
    private Integer id;

    /**
     * 城市编码
     * This field corresponds to the database column tbl_charge_rule.city_code
     */
    private String cityCode;

    /**
     * 服务类型
     * This field corresponds to the database column tbl_charge_rule.service_type_id
     */
    private Integer serviceTypeId;

    /**
     * 渠道名称
     * This field corresponds to the database column tbl_charge_rule.channel_id
     */
    private Integer channelId;

    /**
     * 车辆级别
     * This field corresponds to the database column tbl_charge_rule.car_level_id
     */
    private Integer carLevelId;

    /**
     * 基础价
     * This field corresponds to the database column tbl_charge_rule.lowest_price
     */
    private BigDecimal lowestPrice;

    /**
     * 起步价
     * This field corresponds to the database column tbl_charge_rule.base_price
     */
    private BigDecimal basePrice;

    /**
     * 基础价格包含公里数
     * This field corresponds to the database column tbl_charge_rule.base_kilo
     */
    private Double baseKilo;

    /**
     * 基础价格包含时长数(分钟)
     * This field corresponds to the database column tbl_charge_rule.base_minutes
     */
    private Double baseMinutes;

    /**
     * 超公里单价(每公里单价)
     * This field corresponds to the database column tbl_charge_rule.per_kilo_price
     */
    private BigDecimal perKiloPrice;

    /**
     * 超时间单价(每分钟单价)
     * This field corresponds to the database column tbl_charge_rule.per_minute_price
     */
    private BigDecimal perMinutePrice;

    /**
     * 远途起算公里
     * This field corresponds to the database column tbl_charge_rule.beyond_start_kilo
     */
    private Double beyondStartKilo;

    /**
     * 远途单价
     * This field corresponds to the database column tbl_charge_rule.beyond_per_kilo_price
     */
    private BigDecimal beyondPerKiloPrice;

    /**
     * 夜间时间段开始
     * This field corresponds to the database column tbl_charge_rule.night_start
     */
    private Date nightStart;

    /**
     * 夜间时间段结束
     * This field corresponds to the database column tbl_charge_rule.night_end
     */
    private Date nightEnd;

    /**
     * 夜间超公里加收单价
     * This field corresponds to the database column tbl_charge_rule.night_per_kilo_price
     */
    private BigDecimal nightPerKiloPrice;

    /**
     * 夜间超时间加收单价
     * This field corresponds to the database column tbl_charge_rule.night_per_minute_price
     */
    private BigDecimal nightPerMinutePrice;

    /**
     * 生效时间
     * This field corresponds to the database column tbl_charge_rule.effective_time
     */
    private Date effectiveTime;

    /**
     * 生效状态 0已失效 1未失效
     * This field corresponds to the database column tbl_charge_rule.active_status
     */
    private Integer activeStatus;

    /**
     * 是否不可用 0可用 1不可用
     * This field corresponds to the database column tbl_charge_rule.is_unuse
     */
    private Integer unused;

    /**
     * 创建人id
     * This field corresponds to the database column tbl_charge_rule.creator_id
     */
    private Integer creatorId;

    /**
     * 操作人id
     * This field corresponds to the database column tbl_charge_rule.operator_id
     */
    private Integer operatorId;

    /**
     * 创建时间
     * This field corresponds to the database column tbl_charge_rule.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * This field corresponds to the database column tbl_charge_rule.update_time
     */
    private Date updateTime;
}