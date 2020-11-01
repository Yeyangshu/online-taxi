package com.yeyangshu.apipassenger.controller;

import com.yeyangshu.apipassenger.service.ServiceValuationService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.EstimateRequest;
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
@RequestMapping("/reserve")
@Slf4j
public class EstimatePriceController {

    @Autowired
    ServiceValuationService serviceValuationService;

    /**
     * 预估价格
     * @param request
     * @return
     */
    @PostMapping("/estimate")
    public ResponseResult estimate(@RequestBody EstimateRequest request){
        ResponseResult responseResult;
        try {
            responseResult = serviceValuationService.queryEstimatePrice(request);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("订单预估失败",e);
            responseResult= ResponseResult.fail("订单预估失败");
        }
        return  responseResult;
    }

}
