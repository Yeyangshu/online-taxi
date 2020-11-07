package com.yeyangshu.servicepay.service;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.servicepay.datatransferobject.PayRequest;

import java.util.Map;

/**
 * 支付宝支付服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/6 22:49
 */
public interface AlipayService {

    /**
     * 调用支付宝进行支付
     *
     * @param passengerInfoId
     * @param payCapital
     * @param payGiveFee
     * @param payType
     * @param tradeType
     * @param orderId
     * @param createUser
     * @return 支付结果
     */
    String prePayment(Integer passengerInfoId, Double payCapital, Double payGiveFee,
                   String payType, Integer tradeType, Integer orderId, String createUser);

    /**
     * 支付宝回调
     *
     * @param params 支付宝回调参数
     * @return
     */
    boolean callback(Map<String, String> params);

    /**
     * 验签
     *
     * @param params
     * @return
     */
    Boolean verifyAlipaySign(Map<String, String> params);

    /**
     * 查询支付结果
     *
     * @param orderId
     * @param tradeNo
     * @return
     */
    ResponseResult payResult(Integer orderId, String tradeNo);
}
