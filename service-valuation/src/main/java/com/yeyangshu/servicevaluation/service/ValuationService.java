package com.yeyangshu.servicevaluation.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;

/**
 * 预估价格接口
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:18
 */
public interface ValuationService {

    /**
     * 计价规则
     *
     * @param request
     * @return
     * @throws Exception
     */
    ResponseResult valuationRule(OrderRequest request) throws Exception;
}