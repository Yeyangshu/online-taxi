package com.yeyangshu.serviceorder.controller;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject.OrderRequest;
import com.yeyangshu.serviceorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 18:44
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/estimate-fee")
    public ResponseResult estimateFee(@RequestBody OrderRequest request) throws Exception {
        return orderService.estimateFee(request);
    }

    /**
     * 叫车
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/place-order")
    public ResponseResult callCar(@RequestBody OrderRequest request) throws Exception {
        return orderService.callCar(request);
    }

}