package com.yeyangshu.servicevaluation.controller;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.EstimateRequest;
import com.yeyangshu.servicevaluation.service.EstimateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 23:11
 */
@RestController
@RequestMapping("/estimate")
@Slf4j
public class EstimateController {

    @Autowired
    EstimateService estimateService;

    /**
     * 预估价格
     *
     * @param request
     * @return
     */
    @PostMapping("/price")
    public ResponseResult estimate(@RequestBody EstimateRequest request) {
        ResponseResult responseResult;
        try {
            responseResult = estimateService.estimatePrice(request);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("预估价格失败", e);
            responseResult = ResponseResult.fail("预估价格失败");
        }
        return responseResult;
    }
}
