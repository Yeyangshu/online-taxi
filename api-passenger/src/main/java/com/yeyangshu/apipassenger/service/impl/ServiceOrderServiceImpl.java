package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.ServiceOrderService;
import com.yeyangshu.internalcommon.entity.ResponseResult;
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
public class ServiceOrderServiceImpl implements ServiceOrderService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseResult queryEstimateFee(OrderRequest request) {
        String url = "http://service-order/order/estimate-fee";
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<Object>(request, null), ResponseResult.class).getBody();
        return result;
    }

    @Override
    public ResponseResult createOrder(OrderRequest request) {
        String url = "http://service-order/order/place-order";
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<Object>(request, null), ResponseResult.class).getBody();
        return result;
    }
}