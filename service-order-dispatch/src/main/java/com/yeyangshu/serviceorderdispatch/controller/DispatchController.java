package com.yeyangshu.serviceorderdispatch.controller;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.serviceorderdispatch.datatransferobject.DispatchOrderRequest;
import com.yeyangshu.serviceorderdispatch.service.DispatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/dispatch")
public class DispatchController {

    @Autowired
    DispatchService dispatchService;

    /**
     * 派单
     *
     * @param request
     * @return
     * @throws InterruptedException
     */
    @ResponseBody
    @RequestMapping(value = "/dispatchOrder", produces = "application/json; charset=utf-8")
    public ResponseResult dispatchOrder(@RequestBody DispatchOrderRequest request) throws InterruptedException {
        int orderId = request.getOrderId();
        dispatchService.dispatchOrder(orderId);
        return ResponseResult.success("success");
    }
}
