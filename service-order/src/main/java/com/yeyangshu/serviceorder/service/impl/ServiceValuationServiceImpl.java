package com.yeyangshu.serviceorder.service.impl;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.OrderRequest;
import com.yeyangshu.serviceorder.service.ServiceValuationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceValuationServiceImpl implements ServiceValuationService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult getValuationRule(OrderRequest request) {
        String url = "http://service-valuation/valuation/rule";
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<Object>(request, null), ResponseResult.class).getBody();
        return result;
    }
}