package com.yeyangshu.servicepay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.yeyangshu.internalcommon.constant.PayConst;
import com.yeyangshu.internalcommon.constant.PayEnum;
import com.yeyangshu.internalcommon.constant.PayTypeEnum;
import com.yeyangshu.internalcommon.constant.TradeTypeEnum;
import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.servicepay.dataobject.PassengerWalletRecord;
import com.yeyangshu.internalcommon.entity.servicepay.datatransferobject.PayRequest;
import com.yeyangshu.internalcommon.util.BigDecimalUtil;
import com.yeyangshu.servicepay.service.AlipayService;
import com.yeyangshu.servicepay.service.ServiceWalletService;
import com.yeyangshu.servicepay.service.ThirdPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/6 22:09
 */
@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService {

    /**
     * 商户秘钥
     */
    @Value("${alipay.app-private-key}")
    private String alipayAppPrivateKey;

    /**
     * 支付宝公钥
     */
    @Value("${alipay.public-key}")
    private String alipayPublicKey;

    /**
     * 商户APPID
     */
    @Value("${alipay.app-id}")
    private String alipayAppId;

    /**
     * 商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
     */
    @Value("${alipay.product-code}")
    private String alipayProductCode;

    /**
     * 支付宝网关地址
     */
    @Value("${alipay.gateway}")
    private String alipayGateway;

    /**
     * 交易关闭时间
     */
    @Value("${alipay.close-trade-time}")
    private String alipayCloseTradeTime;

    @Value("${alipay.seller-id}")
    private String sellerId;

    /**
     * 服务器异步通知页面路径
     */
    private static String NOTIFY_URL = "";

    @Autowired
    ServiceWalletService serviceWalletService;

    @Autowired
    ThirdPayService thirdPayService;


    /**
     * 预支付订单
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
    @Override
    public String prePayment(Integer passengerInfoId, Double payCapital, Double payGiveFee,
                             String payType, Integer tradeType, Integer orderId, String createUser) {
        payCapital = payCapital == null ? 0D : payCapital;
        payGiveFee = payGiveFee == null ? 0D : payGiveFee;
        String description = DescriptionFactory.createDescription(payCapital, payGiveFee, "支付宝充值");

        // 记录充值交易记录
        PassengerWalletRecord passengerWalletRecord = serviceWalletService.createWalletRecord(passengerInfoId,
                payCapital, payGiveFee, PayTypeEnum.ALIPAY.getCode(), TradeTypeEnum.RECHARGE.getCode(), description,
                orderId, PayEnum.UN_PAID.getCode(), createUser);

        // 调用支付宝接口
        return routeAlipay(Integer.toString(passengerWalletRecord.getId()), passengerInfoId, payCapital, payGiveFee, payType, tradeType);
    }

    /**
     * 处理callback
     *
     * @param params 支付宝回调参数
     * @return
     */
    @Override
    public boolean callback(Map<String, String> params) {
        boolean flag = false;
        try {
            try {
                thirdPayService.insertAlipay(params);
            } catch (Exception e) {
                log.error("ERROR AlipayServiceImpl - insert data fail, recordId:{}, exception:{}", params.get("out_trade_no"), e.getMessage());
            }

            String body = params.get("body");
            String[] bodyArray = body.split(PayConst.UNDER_LINE);
            // 用户Id
            Integer passengerInfoId = Integer.parseInt(bodyArray[0]);
            // 本金
            Double payCapital = Double.parseDouble(bodyArray[1]);
            // 赠费
            Double payGiveFee = Double.parseDouble(bodyArray[2]);
            // 充值类型
            Integer payType = Integer.parseInt(bodyArray[3]);
            // 记录id
            Integer recordId = Integer.parseInt(bodyArray[4]);
            // 支付宝交易号
            String tradeNo = params.get("trade_no");
            // 支付宝用户号 （非支付宝账号）
            String sellerUserId = params.get("seller_id");
            // 订单支付金额
            String totalFee = params.get("total_amount");
            // 获取订单号=recordId
            String outTradeNo = params.get("out_trade_no");
            // 交易状态
            String tradeStatus = params.get("trade_status");

            if (PayConst.TRADE_SUCCESS.equals(tradeStatus)) {
                log.info("ERROR AlipayServiceImpl - invoke wallet-service handle callback, recordId:{}", params.get("out_trade_no"));
                serviceWalletService.handleCallBack(payType, recordId, tradeNo);
                flag = true;
            } else {
                log.error("ERROR AlipayServiceImpl - handle callback fail, outTradeNo:{}", outTradeNo);
            }
        } catch (Exception e) {
            log.error("ERROR AlipayServiceImpl - handle callback error, exception:{}", e.getMessage());
        }
        return flag;
    }

    /**
     * 验证支付宝回调签名
     *
     * @param params 请求参数map
     * @return 验证结果
     */
    @Override
    public Boolean verifyAlipaySign(Map<String, String> params) {
        Boolean flag = false;
        try {
            flag = AlipaySignature.rsaCheckV1(params, alipayPublicKey, PayConst.CHARSET, PayConst.SIGN_TYPE);
            log.info("INFO AlipayServiceImpl - verify alipay sign, flag:{}", flag);
        } catch (AlipayApiException e) {
            log.error("ERROR AlipayServiceImpl - verify alipay sign fail, recordId:{}", params.get("out_trade_no"));
        }
        return flag;
    }

    @Override
    public ResponseResult payResult(Integer orderId, String tradeNo) {
        return null;
    }


    /**
     * 调用支付宝接口
     *
     * @param recordId
     * @param passengerInfoId
     * @param payCapital
     * @param payGiveFee
     * @param payType
     * @param tradeType
     * @return
     */
    public String routeAlipay(String recordId, Integer passengerInfoId, Double payCapital, Double payGiveFee,
                              String payType, Integer tradeType) {
        // 实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(alipayGateway, alipayAppId, alipayAppPrivateKey,
                PayConst.FORMAT, PayConst.CHARSET, alipayPublicKey, PayConst.SIGN_TYPE);
        // 实例化具体API对应的request类，类名称和接口名称对应，当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

        // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(passengerInfoId + PayConst.UNDER_LINE + payCapital + PayConst.UNDER_LINE + payGiveFee +
                PayConst.UNDER_LINE + payType + PayConst.UNDER_LINE + recordId);
        model.setSubject("逸品出行产品");
        model.setOutTradeNo(recordId);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(BigDecimalUtil.DoubleToString(payCapital));
        model.setProductCode(alipayProductCode);
        model.setSellerId(sellerId);
        request.setBizModel(model);
        // 支付宝回调服务器接口
        request.setNotifyUrl("127.0.0.1:8080/alipay/callback");
        AlipayTradeAppPayResponse response = null;
        try {
            log.info("INFO AlipayServiceImpl - invoke alipay, recordId:{}", recordId);
            response = alipayClient.sdkExecute(request);
            return response.getBody();
        } catch (AlipayApiException e) {
            log.error("ERROR AlipayServiceImpl - invoke alipay fail, recordId:{}", recordId);
            return "invoke alipay fail";
        }
    }
}
