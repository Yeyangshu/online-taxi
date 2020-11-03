package com.yeyangshu.serviceorder.service;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.OrderRequest;
import com.yeyangshu.internalcommon.dto.serviceorder.OrderPrice;
import com.yeyangshu.internalcommon.dto.servicevaluation.Rule;

/**
 * 订单服务接口
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:18
 */
public interface OrderService {

    /**
     * 预估价格
     *
     * @param request 预估价格信息类
     * @return 预估订单价格
     * @throws Exception 异常
     */
    ResponseResult<OrderPrice> estimateFee(OrderRequest request) throws Exception;
    /**
     * 叫车
     *
     * @param request 订单信息
     * @return 叫车
     * @throws Exception 异常
     */
    ResponseResult callCar(OrderRequest request) throws Exception;

}