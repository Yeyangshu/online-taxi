package com.yeyangshu.internalcommon.dto.servicevaluation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 计价结果DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceResult {

    /**
     * 最终计价结果
     */
    private Double price;

}