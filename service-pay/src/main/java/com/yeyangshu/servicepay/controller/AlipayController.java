package com.yeyangshu.servicepay.controller;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.servicepay.datatransferobject.PayRequest;
import com.yeyangshu.internalcommon.util.PayUtil;
import com.yeyangshu.servicepay.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝支付服务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/4 22:03
 */
@RestController
@RequestMapping("/alipay")
@Slf4j
public class AlipayController {

    @Autowired
    AlipayService alipayService;

    /**
     * 调用支付宝SDK进行支付
     *
     * @param payRequest 支付信息接受类
     * @return 支付宝返回结果
     */
    @PostMapping("/payment")
    public ResponseResult payment(@RequestBody PayRequest payRequest) {
        Integer passengerInfoId = payRequest.getPassengerInfoId();
        Double payCapital = payRequest.getPayCapital();
        Double payGiveFee = payRequest.getPayGiveFee();
        String payType = payRequest.getPayType();
        Integer tradeType = payRequest.getTradeType();
        Integer orderId = payRequest.getOrderId();
        String createUser = payRequest.getCreateUser();
        String data = alipayService.prePayment(passengerInfoId, payCapital, payGiveFee,
                payType, tradeType, orderId, createUser);
        return ResponseResult.success(data);
    }


    /**
     * 支付宝支付回调
     * @see {@linktourl https://opendocs.alipay.com/open/204/105301/}
     * 第一步： 在通知返回参数列表中，除去sign、sign_type两个参数外，凡是通知返回回来的参数皆是待验签的参数。
     * 第二步：将剩下参数进行url_decode, 然后进行字典排序，组成字符串，得到待签名字符串
     * 第三步：将签名参数（sign）使用base64解码为字节码串。
     * 第四步：使用RSA的验签方法，通过签名字符串、签名参数（经过base64解码）及支付宝公钥验证签名。
     * 第五步：在步骤四验证签名正确后，必须再严格按照如下描述校验通知数据的正确性。
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     * 3、校验通知中的seller_id（或者seller_email)
     * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     * PS:（针对异步通知）程序执行完后必须打印输出“success”（不包含引号）。如果商户反馈给支付宝的字符不是success这7个字符，
     * 支付宝服务器会不断重发通知，直到超过24小时22分钟。
     * 一般情况下，25小时以内完成8次通知（通知的间隔频率一般是：4m,10m,10m,1h,2h,6h,15h）；
     */
    @RequestMapping(value = "/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) {
        String returnStr = "failure";
        // 获取支付宝异步通知收到的待验证所有参数
        Map<String, String> params = PayUtil.convertRequestParamsToMap(request);
        log.info("INFO AlipayController - Alipay callback params, params:[{}]", params.toString());

        // 调用支付宝 SDK 验证签名
        boolean verifyflag = alipayService.verifyAlipaySign(params);
        // 验签成功
        if (verifyflag) {
            boolean flag = false;
            try {
                // 处理支付成功逻辑
                flag = alipayService.callback(params);
                if (flag) {
                    returnStr = "success";
                    log.info("INFO AlipayController - alipay return success, recordId:{}", params.get("out_trade_no"));
                } else {
                    log.info("INFO AlipayController - alipay response handle fail, wait alipay callback, recordId:{}", params.get("out_trade_no"));
                }
            } catch (Exception e) {
                log.info("INFO AlipayController - alipay response handle fail, wait alipay callback, recordId:{}, exception:{}", params.get("out_trade_no"), e.getMessage());
            }
        }
        // 支付宝要求必须返回success，不然就会一直回调
        try {
            PrintWriter out = response.getWriter();
            out.println(returnStr);
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("ERROR AlipayController - Alipay callback result params error, exception:{}", e.getMessage());
        }
    }
}
