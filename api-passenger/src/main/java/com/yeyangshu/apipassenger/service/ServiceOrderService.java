package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;

/**
 * 订单服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/2 20:26
 */
public interface ServiceOrderService {

    /**
     * 计价
     *
     * @param request
     * @return
     */
    ResponseResult queryEstimateFee(OrderRequest request);

    /**
     * 下单
     *
     * @param request
     * @return
     */
    ResponseResult createOrder(OrderRequest request);
}