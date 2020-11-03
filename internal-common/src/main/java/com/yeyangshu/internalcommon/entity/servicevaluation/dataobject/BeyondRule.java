package com.yeyangshu.internalcommon.entity.servicevaluation.dataobject;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 远途服务费
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 22:02
 */
@Data
public class BeyondRule {

    /**
     * 远途起算公里（公里）
     */
    private Double startKilo;

    /**
     * 远途单价（元/公里）
     */
    private BigDecimal perKiloPrice;
}
