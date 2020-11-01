package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.ServiceValuationService;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.EstimateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 调用计价服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 22:52
 */
@Service
public class ServiceValuationServiceImpl implements ServiceValuationService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseResult queryEstimatePrice(EstimateRequest request) {
        String url = "http://service-valuation/order/estimate";
        ResponseResult responseResult = restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(null, null), ResponseResult.class).getBody();
        return responseResult;
    }
}
