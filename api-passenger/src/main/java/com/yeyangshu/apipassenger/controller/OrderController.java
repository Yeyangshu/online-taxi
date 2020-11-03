package com.yeyangshu.apipassenger.controller;

import com.yeyangshu.apipassenger.service.OrderService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 预估价格
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:05
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 预估价格
     *
     * @param request
     * @return
     */
    @PostMapping("/estimate-price")
    public ResponseResult estimate(@RequestBody OrderRequest request) {
        ResponseResult responseResult;
        try {
            responseResult = orderService.queryEstimateFee(request);
        } catch (Exception e) {
            log.error("订单预估失败", e);
            responseResult = ResponseResult.fail("订单预估失败");
        }
        return responseResult;
    }

    /**
     * 下单叫车
     *
     * @param request
     * @return
     */
    @PostMapping("/place-order")
    public ResponseResult createOrder(@RequestBody OrderRequest request) {
        ResponseResult responseResult;
        try {
            responseResult = orderService.createOrder(request);
        } catch (Exception e) {
            log.error("下单叫车失败",e);
            responseResult= ResponseResult.fail("下单叫车失败");
        }
        return responseResult;
    }

}