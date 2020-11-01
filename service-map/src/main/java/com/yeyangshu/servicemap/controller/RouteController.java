package com.yeyangshu.servicemap.controller;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.EstimateRequest;
import com.yeyangshu.internalcommon.dto.servicemap.RouteInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Route 服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:49
 */
@RestController
@RequestMapping("/map")
@Slf4j
public class RouteController {

    /**
     * 预估价格
     * @param request
     * @return
     */
    @PostMapping("/route")
    public ResponseResult generateRoute(@RequestBody EstimateRequest request){
        RouteInfo route = new RouteInfo(1000.00, 120.0);
        return new ResponseResult().success(route);
    }
}
