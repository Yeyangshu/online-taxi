package com.yeyangshu.servicevaluation.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.EstimateRequest;
import com.yeyangshu.internalcommon.dto.serviceorder.OrderPrice;

/**
 * 预估价格接口
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:18
 */
public interface EstimateService {

    /**
     * 订单预估
     *
     * @param request 预估价格信息类
     * @return 预估订单价格
     * @throws Exception 异常
     */
    ResponseResult<OrderPrice> estimatePrice(EstimateRequest request) throws Exception;
}
