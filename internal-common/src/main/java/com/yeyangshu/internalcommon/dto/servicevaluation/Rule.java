package com.yeyangshu.internalcommon.dto.servicevaluation;

import lombok.Data;

import java.util.List;

/**
 * 计费规则
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 22:02
 */
@Data
public class Rule {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 计费规则主键类
     */
    private KeyRule keyRule;

    /**
     * 基础计费
     */
    private BasicRule basicRule;

    /**
     * 计费方法
     */
    private PriceRule priceRule;

    /**
     * 远途服务费
     */
    private BeyondRule beyondRule;

    /**
     * 夜间服务费
     */
    private NightRule nightRule;

    /**
     * 标签费用：1201版本
     */
    private List<TagPrice> tagPrices;

}