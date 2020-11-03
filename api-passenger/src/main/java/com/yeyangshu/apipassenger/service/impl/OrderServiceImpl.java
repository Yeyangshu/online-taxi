package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.OrderService;
import com.yeyangshu.apipassenger.service.ServiceOrderService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ServiceOrderService serviceOrderService;

    /**
     * 预估价格
     *
     * @param request 订单信息
     * @return 预估价格
     */
    @Override
    public ResponseResult queryEstimateFee(OrderRequest request) {
        ResponseResult responseResult;
        // 调用估价服务，预估价格
        responseResult = serviceOrderService.queryEstimateFee(request);
        return responseResult;
    }

    /**
     * 下单
     *
     * @param request 订单信息
     * @return 下单信息
     */
    @Override
    public ResponseResult createOrder(OrderRequest request) {
        ResponseResult responseResult;
        // 调用计价服务，查新预估价格，防止薅羊毛
        responseResult = serviceOrderService.createOrder(request);
        return responseResult;
    }
}