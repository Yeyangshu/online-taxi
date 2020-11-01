package com.yeyangshu.serviceorder.controller;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.EstimateRequest;
import com.yeyangshu.internalcommon.dto.servicepassengeruser.request.PhoneNumberRequest;
import com.yeyangshu.serviceorder.service.OrderService;
import com.yeyangshu.serviceorder.service.ServiceMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/estimate")
    public ResponseResult send(@RequestBody EstimateRequest request) throws Exception {
        return orderService.estimateOrderCreate(request);
    }

}
