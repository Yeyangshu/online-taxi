package com.yeyangshu.servicevaluation.service.impl;

import com.yeyangshu.internalcommon.constant.CommonStatusEnum;
import com.yeyangshu.internalcommon.constant.OrderServiceTypeEnum;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.EstimateRequest;
import com.yeyangshu.internalcommon.dto.serviceorder.BaseOrderInfo;
import com.yeyangshu.internalcommon.dto.serviceorder.OrderPrice;
import com.yeyangshu.internalcommon.dto.servicevaluation.Rule;
import com.yeyangshu.servicevaluation.service.EstimateService;
import com.yeyangshu.servicevaluation.service.ServiceMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预估价格实现类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:20
 */
@Service
@Slf4j
public class EstimateServiceImpl implements EstimateService {

    @Autowired
    ServiceMapService serviceMapService;

    /**
     * 订单预估
     *
     * @param request 预估价格信息类
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<OrderPrice> estimatePrice(EstimateRequest request) throws Exception {
        log.info("estimate order request:" + request);
        OrderPrice orderPrice = new OrderPrice();
        ResponseResult responseResult;
        if (null == request.getOrderId()) {
            // 判断不是半日租或全日租
            if (OrderServiceTypeEnum.CHARTERED_CAR.getCode() != request.getServiceTypeId()
                    && OrderServiceTypeEnum.THROUGHOUT_THE_DAY.getCode() != request.getServiceTypeId()) {
                // 调用地图服务，获取路途长度和行驶时间
                try {
                    responseResult = serviceMapService.getRoute(request);
                    if (CommonStatusEnum.SUCCESS.getCode() != responseResult.getCode()) {
                        return responseResult;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("测量距离失败！");
                }
            }
        }
        orderPrice.setOrderId(1);
        orderPrice.setPrice(20.00);
        return ResponseResult.success(orderPrice);
    }
}
