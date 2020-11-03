package com.yeyangshu.serviceorder.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.OrderRequest;

/**
 * 计价服务
 */
public interface ServiceValuationService {

    /**
     * 获取计价规则
     *
     * @param request
     * @return
     */
    ResponseResult getValuationRule(OrderRequest request);

    ResponseResult donePrice(OrderRequest request);
}