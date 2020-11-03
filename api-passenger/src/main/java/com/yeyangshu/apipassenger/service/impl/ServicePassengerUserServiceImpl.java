package com.yeyangshu.apipassenger.service.impl;

import com.yeyangshu.apipassenger.service.ServicePassengerUserService;
import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 调用登录服务
 *
 * @author yeyangshu
 */
@Service
public class ServicePassengerUserServiceImpl implements ServicePassengerUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseResult queryPassenger(TokenRequest request) {
        String url = "http://service-passenger-user/passenger/query";
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<Object>(request, null), ResponseResult.class).getBody();
        return result;
    }
}