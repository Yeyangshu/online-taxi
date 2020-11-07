package com.yeyangshu.internalcommon.entity.servicepay.datatransferobject;

import lombok.Data;

/**
 * 支付信息接受类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/6 22:39
 */
@Data
public class PayRequest {

    /**
     * 用户ID
     */
    private Integer passengerInfoId;

    /**
     * 支付本金
     */
    private Double payCapital;

    /**
     * 赠费
     */
    private Double payGiveFee;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 交易类型
     */
    private Integer tradeType;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 创建用户
     */
    private String createUser;
}
