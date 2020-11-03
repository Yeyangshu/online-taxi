package com.yeyangshu.serviceorder.service.impl;

import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.servicepassengeruser.PassengerInfo;
import com.yeyangshu.serviceorder.service.ServicePassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ServicePassengerServiceImpl implements ServicePassengerService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseResult selectByPrimaryKey(Integer passengerId) {
        String url = "http://service-passenger-user/passenger/query/" + passengerId;
        log.info("invoke service-passenger-user service, url is " + url);
        ResponseResult responseResult = restTemplate.exchange(url, HttpMethod.GET,
                new HttpEntity<>(null, null), ResponseResult.class).getBody();
        return responseResult;
    }
}