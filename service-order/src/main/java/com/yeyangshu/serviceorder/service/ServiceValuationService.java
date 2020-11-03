package com.yeyangshu.serviceorder.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject.OrderRequest;

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