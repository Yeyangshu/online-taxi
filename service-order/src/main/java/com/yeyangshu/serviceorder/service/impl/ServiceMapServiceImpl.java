package com.yeyangshu.serviceorder.service.impl;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.OrderRequest;
import com.yeyangshu.serviceorder.service.ServiceMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:45
 */
@Service
public class ServiceMapServiceImpl implements ServiceMapService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult getRoute(OrderRequest request) {
        String url = "http://service-map/map/route";
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<Object>(request, null), ResponseResult.class).getBody();
        return result;
    }
}