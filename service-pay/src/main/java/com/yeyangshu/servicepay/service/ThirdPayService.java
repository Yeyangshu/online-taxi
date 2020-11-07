package com.yeyangshu.servicepay.service;

import java.util.Map;

/**
 * 第单方支付纪录入库接口
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/7 11:40
 */
public interface ThirdPayService {

    void insertAlipay(Map<String, String> params);

//    void insertWeixinpay(ScanPayResData scanPayResData);
}
