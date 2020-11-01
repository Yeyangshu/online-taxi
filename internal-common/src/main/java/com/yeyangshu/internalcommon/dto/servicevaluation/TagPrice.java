package com.yeyangshu.internalcommon.dto.servicevaluation;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 标签费用
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 22:02
 */
@Data
public class TagPrice {
    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签费用
     */
    private BigDecimal price;
}
