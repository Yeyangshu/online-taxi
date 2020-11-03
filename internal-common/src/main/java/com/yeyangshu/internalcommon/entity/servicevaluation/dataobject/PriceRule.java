package com.yeyangshu.internalcommon.entity.servicevaluation.dataobject;

import com.yeyangshu.internalcommon.entity.servicevaluation.TimeRule;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 计费方法
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 22:02
 */
@Data
public class PriceRule {

    /**
     * 超公里单价（元/公里）
     */
    private BigDecimal perKiloPrice;

    /**
     * 超时间单价（元/分钟）
     */
    private BigDecimal perMinutePrice;

    /**
     * 分段计时规则
     */
    private List<TimeRule> timeRules;
}
