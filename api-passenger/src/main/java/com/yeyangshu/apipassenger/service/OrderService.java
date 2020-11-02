package com.yeyangshu.apipassenger.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.OrderRequest;

/**
 * 预估价格服务接口
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/2 20:25
 */
public interface OrderService {

    /**
     * 预估价格
     *
     * @param request 订单信息
     * @return 预估价格结果
     */
    ResponseResult queryEstimateFee(OrderRequest request);

    /**
     * 下单
     *
     * @param request 订单信息
     * @return 预估价格结果
     */
    ResponseResult createOrder(OrderRequest request);
}
