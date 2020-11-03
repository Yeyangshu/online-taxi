package com.yeyangshu.serviceorder.service.impl;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject.OrderRequest;
import com.yeyangshu.serviceorder.service.ServiceValuationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yeyangshu
 */
@Service
public class ServiceValuationServiceImpl implements ServiceValuationService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 预估价格
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult getValuationRule(OrderRequest request) {
        String url = "http://service-valuation/valuation/rule";
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<Object>(request, null), ResponseResult.class).getBody();
        return result;
    }

    /**
     * 最终定价(未实现)
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult donePrice(OrderRequest request) {
        String url = "http://service-valuation/valuation/rule";
        ResponseResult result = restTemplate.exchange(url, HttpMethod.POST,
                new HttpEntity<Object>(request, null), ResponseResult.class).getBody();
        return result;
    }
}