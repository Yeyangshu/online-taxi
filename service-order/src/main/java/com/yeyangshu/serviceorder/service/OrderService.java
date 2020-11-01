package com.yeyangshu.serviceorder.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.EstimateRequest;
import com.yeyangshu.internalcommon.dto.serviceorder.OrderPrice;

/**
 * 订单服务接口
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:18
 */
public interface OrderService {

    /**
     * 订单预估
     *
     * @param request 预估价格信息类
     * @return 预估订单价格
     * @throws Exception 异常
     */
    ResponseResult<OrderPrice> estimateOrderCreate(EstimateRequest request) throws Exception;
}
