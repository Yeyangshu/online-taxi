package com.yeyangshu.serviceorder.service.impl;

import com.yeyangshu.internalcommon.constant.CommonStatusEnum;
import com.yeyangshu.internalcommon.constant.OrderServiceTypeEnum;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.EstimateRequest;
import com.yeyangshu.internalcommon.dto.serviceorder.BaseOrderInfo;
import com.yeyangshu.internalcommon.dto.serviceorder.OrderPrice;
import com.yeyangshu.internalcommon.dto.servicevaluation.Rule;
import com.yeyangshu.serviceorder.service.OrderService;
import com.yeyangshu.serviceorder.service.ServiceMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单服务实现类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:20
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

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
    public ResponseResult<OrderPrice> estimateOrderCreate(EstimateRequest request) throws Exception {
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
            // 校验信息，#TODO
            BaseOrderInfo baseOrderInfo = new BaseOrderInfo();
            baseOrderInfo.setCityName("杭州");
            baseOrderInfo.setServiceTypeName("实时用车");
            baseOrderInfo.setChannelName("智行");
            baseOrderInfo.setCarLevelName("舒适性");
            // 调用计价服务，计算计价规则
            Rule rule = new Rule();

            // 创建订单和计价规则

        }
        return ResponseResult.success(orderPrice);
    }
}
