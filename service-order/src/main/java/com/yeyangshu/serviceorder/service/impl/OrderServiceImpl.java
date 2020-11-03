package com.yeyangshu.serviceorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeyangshu.internalcommon.constant.*;
import com.yeyangshu.internalcommon.dto.ResponseResult;
import com.yeyangshu.internalcommon.dto.apipassenger.request.OrderRequest;
import com.yeyangshu.internalcommon.dto.serviceorder.OrderPrice;
import com.yeyangshu.internalcommon.dto.serviceorder.OrderRuleMirror;
import com.yeyangshu.internalcommon.dto.serviceorder.dataobject.Order;
import com.yeyangshu.internalcommon.dto.servicepassengeruser.PassengerInfo;
import com.yeyangshu.internalcommon.dto.servicevaluation.KeyRule;
import com.yeyangshu.internalcommon.dto.servicevaluation.Rule;
import com.yeyangshu.internalcommon.dto.servicevaluation.TagPrice;
import com.yeyangshu.internalcommon.util.EncryptUtil;
import com.yeyangshu.serviceorder.IdGenerator;
import com.yeyangshu.internalcommon.util.ResponseResultHelper;
import com.yeyangshu.serviceorder.dao.OrderDao;
import com.yeyangshu.serviceorder.mapper.OrderMapper;
import com.yeyangshu.serviceorder.mapper.OrderRuleMirrorMapper;
import com.yeyangshu.serviceorder.service.OrderService;
import com.yeyangshu.serviceorder.service.ServiceMapService;
import com.yeyangshu.serviceorder.service.ServicePassengerService;
import com.yeyangshu.serviceorder.service.ServiceValuationService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    private static final String PASSENGERS_IS_NULL = "乘客信息为空";

    private static final String VALUATION_RULES_CHANGE = "当前预估价格已变化";

    private boolean flag;

    /**
     * 是否生成订单状态，true：已生成，false：未生成
     */
    private boolean sate;

    @Autowired
    ServiceMapService serviceMapService;

    @Autowired
    ServiceValuationService serviceValuationService;

    @Autowired
    ServicePassengerService servicePassengerService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderRuleMirrorMapper orderRuleMirrorMapper;

    /**
     * 订单预估
     *
     * @param request 预估价格信息类
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult<OrderPrice> estimateFee(OrderRequest request) throws Exception {
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
                    log.error("测量距离失败！");
                }
            }

            // 调用计价服务获取计价规则
            Rule rule = null;
            try {
                responseResult = serviceValuationService.getValuationRule(request);
                rule = ResponseResultHelper.parse(responseResult, Rule.class);
                log.info("rule:" + rule);
            } catch (Exception e) {
                log.error("调用计价服务获取计价失败");
            }

            // 创建订单
            try {
                responseResult = createOrderAndOrderRuleMirror(request, rule);
                if (CommonStatusEnum.SUCCESS.getCode() != responseResult.getCode()) {
                    return responseResult;
                }
                Object data = responseResult.getData();
                request.setOrderId(Integer.valueOf(data.toString()));
            } catch (Exception e) {
                log.error("创建订单失败");
            }
        } else {
            // 儿童和女性用车
            if (null != request.getUserFeature()) {
                // 修改订单服务类型与标签
                responseResult = updateOrderServiceAndUserFeature(request);
                if (CommonStatusEnum.SUCCESS.getCode() != responseResult.getCode()) {
                    return responseResult;
                }
            }
            // 修改计价规则
            // 存储计价信息
//            responseResult = updateOrderRuleMirror(request);
//            if (CommonStatusEnum.SUCCESS.getCode() == responseResult.getCode()) {
//                sate = true;
//            } else {
//                return responseResult;
//            }
        }
        // 已生成订单id
        if (sate) {
            try {
                // 调用计价服务开始计价
//                PriceResult priceResult = otherInterfaceTask.getOrderPrice(request.getOrderId());
//                orderPrice.setPrice(priceResult.getPrice());
                orderPrice.setPrice(20.00);
            } catch (Exception e) {
                log.error("计价接口发生错误", e);
                throw e;
            }
        }
        orderPrice.setOrderId(request.getOrderId());
        return ResponseResult.success(orderPrice);
////        Rule rule = new Rule();
//        BasicRule basicRule = new BasicRule();
//        basicRule.setLowestPrice(BigDecimal.valueOf(10.00));
//        basicRule.setBasePrice(BigDecimal.valueOf(10.00));
//        basicRule.setKilos(5.00);
//        basicRule.setMinutes(15.00);
//        rule.setBasicRule(basicRule);
//        orderPrice.setOrderId(1);
//        orderPrice.setPrice(20.00);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("orderPrice", JSONObject.toJSONString(orderPrice));
//        jsonObject.put("rule", JSONObject.toJSONString(rule));
//        return ResponseResult.success(jsonObject);
    }

    @Override
    public ResponseResult callCar(OrderRequest request) throws Exception {
        ResponseResult responseResult;

        Integer orderId = request.getOrderId();
        if (StringUtils.isEmpty(orderId)) {
            log.error("订单Id为空");
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), "订单Id为空");
        }
        // 查找订单
        Order order = orderMapper.selectByPrimaryKey(orderId);
        Rule newRule = null;

        try {
            // 数据库中查找预估价格的计价规则
            Rule oldRule = parse(orderRuleMirrorMapper.selectByPrimaryKey(orderId).getRule(), Rule.class);
            KeyRule keyRule = oldRule.getKeyRule();
            request.setCarLevelId(keyRule.getCarLevelId());
            request.setCarLevelName(keyRule.getCarLevelName());
            request.setServiceTypeId(keyRule.getServiceTypeId());
            request.setServiceTypeName(keyRule.getServiceTypeName());
            request.setChannelId(keyRule.getChannelId());
            request.setChannelName(keyRule.getChannelName());
            request.setCityCode(keyRule.getCityCode());
            request.setCityName(keyRule.getCityName());
            request.setUserFeature(order.getUserFeature());
            responseResult = serviceValuationService.getValuationRule(request);
            // 调用计价服务重新计价，防止薅羊毛
            if (CommonStatusEnum.SUCCESS.getCode() != responseResult.getCode()) {
                return responseResult;
            }
            // 解析老的计价规则
            newRule = ResponseResultHelper.parse(responseResult, Rule.class);
            flag = false;
            responseResult = insertOrUpdateOrderRuleMirror(newRule, orderId);
            if (CommonStatusEnum.SUCCESS.getCode() != responseResult.getCode()) {
                return responseResult;
            }
            if (!assertSameRule(oldRule, newRule)) {
                log.error("计价规则有变，originalRule={}，newestRule={}", oldRule, newRule);
                return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), VALUATION_RULES_CHANGE);
            }
        } catch (Exception e) {
            log.error("重新预估价格失败");
        }

        // 订单开始
        order.setStartTime(new Date());
        if (OrderEnum.SERVICE_TYPE.getCode() == request.getServiceTypeId()) {
            order.setOrderStartTime(new Date());
        }
        order.setStatus(OrderStatusEnum.STATUS_ORDER_START.getCode());
        order.setOrderType(request.getOrderType());
        // 判断是否为他人订单
        String otherPhone = null;
        if (request.getOrderType() == OrderEnum.ORDER_TYPE_OTHER.getCode()) {
            if (StringUtils.isEmpty(request.getOtherPhone())) {
                log.error("给他人叫车，手机号为空！");
                return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), "给它人叫车，手机号不能为空！");
            }
            otherPhone = EncryptUtil.encryptionPhoneNumber(request.getOtherPhone());
            order.setOtherName(request.getOtherName());
        } else {
            // 调用服务
            responseResult = servicePassengerService.selectByPrimaryKey(order.getPassengerInfoId());
            PassengerInfo passengerInfo = ResponseResultHelper.parse(responseResult, PassengerInfo.class);
            otherPhone = passengerInfo.getPhone();
            if (StringUtils.isEmpty(otherPhone)) {
                order.setOtherName(passengerInfo.getPassengerName());
            }
        }
        order.setOtherPhone(otherPhone);
        orderMapper.updateByPrimaryKeySelective(order);

        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(String.format("%s:%s:%s",
                OrderRuleNameConstant.PREFIX, OrderRuleNameConstant.RULE, orderId));
        ops.set(new ObjectMapper().writeValueAsString(newRule));
        // 最终定价(未实现)
        responseResult = serviceValuationService.donePrice(request);
        if (CommonStatusEnum.SUCCESS.getCode() != responseResult.getCode()) {
            return responseResult;
        }

        // 调用派单服务开始派单

        return ResponseResult.success("success");
    }

    /**
     * 创建订单和计价规则
     *
     * @param request
     * @param rule
     * @return
     * @throws Exception
     */
    public ResponseResult createOrderAndOrderRuleMirror(OrderRequest request, Rule rule) throws Exception {
        log.info("create order request:" + request);
        ResponseResult responseResult;
        Integer orderId;
        if (OrderServiceTypeEnum.CHARTERED_CAR.getCode() == request.getServiceTypeId()
                || OrderServiceTypeEnum.THROUGHOUT_THE_DAY.getCode() == request.getServiceTypeId()) {
            request.setEndAddress("-");
            request.setEndLongitude(request.getStartLongitude());
            request.setEndLatitude(request.getStartLatitude());
        }
        // 创建订单
        responseResult = createOrder(request);
        if (CommonStatusEnum.SUCCESS.getCode() == responseResult.getCode()) {
            flag = true;
            Order order = ResponseResultHelper.parse(responseResult, Order.class);
            orderId = order.getId();
            // 插入更新计价规则
            responseResult = insertOrUpdateOrderRuleMirror(rule, orderId);
            if (CommonStatusEnum.SUCCESS.getCode() != responseResult.getCode()) {
                return responseResult;
            }
            sate = true;
            BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(String.format("%s:%s:%s",
                    OrderRuleNameConstant.PREFIX, OrderRuleNameConstant.RULE, orderId));
            ops.set(JSONObject.toJSONString(rule), 1, TimeUnit.HOURS);
        } else {
            return responseResult;
        }
        return responseResult.setData(orderId);
    }

    /**
     * 创建订单
     *
     * @param request
     * @return
     */
    private ResponseResult createOrder(OrderRequest request) throws Exception {
        if (!StringUtils.isEmpty(request.getPassengerInfoId().toString())) {
            log.info("passenger info id = " + request.getPassengerInfoId());
            ResponseResult result = servicePassengerService.selectByPrimaryKey(request.getPassengerInfoId());
            PassengerInfo passengerInfo = ResponseResultHelper.parse(result, PassengerInfo.class);
            if (passengerInfo == null) {
                return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), PASSENGERS_IS_NULL);
            }
            String orderNumber = idGenerator.nextId();
            Order order = new Order();
            order.setOrderNumber(orderNumber);
            order.setPassengerInfoId(request.getPassengerInfoId());
            order.setPassengerPhone(passengerInfo.getPhone());
            order.setDeviceCode(request.getDeviceCode());
            order.setUserLongitude(request.getUserLongitude());
            order.setUserLatitude(request.getUserLatitude());
            order.setStartLongitude(request.getStartLongitude());
            order.setStartLatitude(request.getStartLatitude());
            order.setStartAddress(request.getStartAddress());
            order.setServiceType(request.getServiceTypeId());
            order.setStatus(OrderStatusEnum.CALL_ORDER_FORECAST.getCode());
            Date startDate = new Date();
            if (OrderEnum.SERVICE_TYPE.getCode() != request.getServiceTypeId()) {
                startDate = request.getOrderStartTime();
            }
            order.setOrderStartTime(startDate);
            order.setOrderChannel(request.getChannelId());
            order.setServiceType(request.getServiceTypeId());
            order.setEndLongitude(request.getEndLongitude());
            order.setEndLatitude(request.getEndLatitude());
            order.setEndAddress(request.getEndAddress());
            order.setSource(request.getSource());
            order.setCreateTime(new Date());
            if (null != request.getUserFeature()) {
                order.setUserFeature(request.getUserFeature());
            }
            try {
                orderDao.insertOrder(order);
            } catch (Exception e) {
                log.info("新增订单失败");
            }
            return ResponseResult.success(order);
        } else {
            log.error(PASSENGERS_IS_NULL + "乘客手机号：" + request.getPassengerPhone());
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), PASSENGERS_IS_NULL);
        }
    }

    /**
     * 插入更新计价规则
     *
     * @param rule
     * @param orderId
     * @return
     */
    public ResponseResult insertOrUpdateOrderRuleMirror(Rule rule, Integer orderId) {
        log.info("Rule：" + rule);
        OrderRuleMirror orderRuleMirror = new OrderRuleMirror();
        try {
            orderRuleMirror.setOrderId(orderId);
            orderRuleMirror.setRuleId(rule.getId());
            orderRuleMirror.setRule(new ObjectMapper().writeValueAsString(rule));
            int up = 0;
            if (!flag) {
                up = orderRuleMirrorMapper.updateByPrimaryKeySelective(orderRuleMirror);
            } else {
                orderRuleMirror.setCreateTime(new Date());
                up = orderRuleMirrorMapper.insertSelective(orderRuleMirror);
            }
            if (up == 0) {
                return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), "更新计价规则失败");
            }
        } catch (JsonProcessingException e) {
            log.error("插入或更新计价规则失败");
        }
        return ResponseResult.success("success");
    }

    /**
     * 修改订单服务类型与标签
     *
     * @param request
     * @return
     */
    public ResponseResult updateOrderServiceAndUserFeature(OrderRequest request) {
        log.info("OrderRequest={}", request);
        int i;
        Order newOrder = orderMapper.selectByPrimaryKey(request.getOrderId());
        newOrder.setUserFeature(request.getUserFeature());
        i = orderMapper.updateByPrimaryKeySelective(newOrder);
        if (i == 0) {
            return ResponseResult.fail(CommonStatusEnum.FAIL.getCode(), "更新失败");
        }
        return ResponseResult.success("success");
    }

    /**
     * 字符串转实体类
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> T parse(String jsonStr, Class<T> clazz) {
        ObjectMapper om = new ObjectMapper();
        T readValue = null;
        try {
            readValue = om.readValue(jsonStr, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readValue;
    }

    /**
     * 判断计价规则是否一致
     *
     * @param oldRule 计价规则1
     * @param newRule 计价规则2
     * @return true：一致，否则false
     */
    @SneakyThrows
    private boolean assertSameRule(Rule oldRule, Rule newRule) {
        Rule oldRuleClone = new Rule();
        BeanUtils.copyProperties(oldRule, oldRuleClone);
        oldRuleClone.setTagPrices(null);

        Rule newRuleClone = new Rule();
        BeanUtils.copyProperties(newRule, newRuleClone);
        newRuleClone.setTagPrices(null);

        //非标签计价比较
        ObjectMapper mapper = new ObjectMapper();
        if (!ObjectUtils.nullSafeEquals(mapper.writeValueAsString(newRuleClone), mapper.writeValueAsString(oldRuleClone))) {
            return false;
        }

        //标签计价比较
        List<TagPrice> oldRuleTagPrices = Optional.ofNullable(oldRule.getTagPrices()).orElse(new ArrayList<>());
        List<TagPrice> newRuleTagPrices = Optional.ofNullable(newRule.getTagPrices()).orElse(new ArrayList<>());
        for (TagPrice tagPrice : oldRuleTagPrices) {
            if (newRuleTagPrices.stream().anyMatch(p -> p.getName().equals(tagPrice.getName()) && p.getPrice().compareTo(tagPrice.getPrice()) != 0)) {
                return false;
            }
        }
        return true;
    }

}