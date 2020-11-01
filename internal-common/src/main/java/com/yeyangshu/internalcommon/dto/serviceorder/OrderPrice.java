package com.yeyangshu.internalcommon.dto.serviceorder;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能描述
 *
 * @date 2018/8/25
 */
@Data
@NoArgsConstructor
public class OrderPrice {

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单价格
     */
    private Double price;
}
