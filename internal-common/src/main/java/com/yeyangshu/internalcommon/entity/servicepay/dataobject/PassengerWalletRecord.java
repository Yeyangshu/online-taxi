package com.yeyangshu.internalcommon.entity.servicepay.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 充值交易记录
 */
@Data
public class PassengerWalletRecord {

    /**
     * 数据库主键id
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer passengerInfoId;

    /**
     * 第三方支付ID
     */
    private String transactionId;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 本金
     */
    private Double payCapital;

    /**
     * 赠费
     */
    private Double payGiveFee;

    /**
     * 退款本金
     */
    private Double refundCapital;

    /**
     * 退款赠费
     */
    private Double refundGiveFee;

    /**
     * 充值折扣
     */
    private Double rechargeDiscount;

    /**
     * 支付方式，1：微信 ，2：账户余额，3：平台账户，4：支付宝'
     */
    private Integer payType;

    /**
     * 支付状态，1：已支付 ，0：未支付
     */
    private Integer payStatus;

    /**
     * 交易类型：1充值，2消费，3退款，4订单冻结，5订单补扣，6尾款支付，7解冻
     */
    private Integer tradeType;

    /**
     * 交易原因
     */
    private String tradeReason;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 订单Id
     */
    private Integer orderId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}