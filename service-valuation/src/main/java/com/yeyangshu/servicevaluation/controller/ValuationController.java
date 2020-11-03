package com.yeyangshu.servicevaluation.controller;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.servicevaluation.service.ValuationService;
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
@RequestMapping("/valuation")
@Slf4j
public class ValuationController {

    @Autowired
    ValuationService valuationService;

    /**
     * 预估价格
     *
     * @param request
     * @return
     */
    @PostMapping("/rule")
    public ResponseResult valuation(@RequestBody OrderRequest request) {
        ResponseResult responseResult;
        try {
            responseResult = valuationService.valuationRule(request);
        } catch (Exception e) {
            log.error("获取计价规则失败", e);
            responseResult = ResponseResult.fail("获取计价规则失败");
        }
        return responseResult;
    }
}