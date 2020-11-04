package com.yeyangshu.serviceorderdispatch.task;

import com.alibaba.fastjson.JSONObject;
import com.yeyangshu.internalcommon.constant.DispatchConstant;
import com.yeyangshu.internalcommon.constant.IdentityEnum;
import com.yeyangshu.internalcommon.constant.MessageType;
import com.yeyangshu.internalcommon.constant.OrderTypeEnum;
import com.yeyangshu.internalcommon.entity.servicefile.datatransferobject.BoundPhoneDto;
import com.yeyangshu.internalcommon.entity.servicemap.datatransferobject.OrderRequest;
import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.Order;
import com.yeyangshu.internalcommon.entity.serviceorderdispatch.dataobject.CarInfo;
import com.yeyangshu.internalcommon.entity.serviceorderdispatch.datatransferobject.DriverData;
import com.yeyangshu.internalcommon.entity.serviceorderdispatch.datatransferobject.OrderDto;
import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.OrderRulePrice;
import com.yeyangshu.internalcommon.entity.serviceorderdispatch.datatransferobject.PushRequest;
import com.yeyangshu.internalcommon.entity.servicepassengeruser.dataobject.PassengerInfo;
import com.yeyangshu.internalcommon.entity.servicevaluation.dataobject.ChargeRule;
import com.yeyangshu.internalcommon.util.DateUtils;
import com.yeyangshu.internalcommon.util.EncryptUtil;
import com.yeyangshu.serviceorderdispatch.lock.RedisLock;
import com.yeyangshu.serviceorderdispatch.service.DispatchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 强派订单
 */
@Slf4j
public class OrderForceTask extends AbstractTask {

    public OrderForceTask(int orderId, int type) {
        this.orderId = orderId;
        this.type = type;
    }

    /**
     * 派单
     *
     * @param order
     * @param orderRulePrice
     * @param taskCondition
     * @param round
     * @return
     */
    @Override
    public boolean sendOrder(Order order, OrderRulePrice orderRulePrice, TaskCondition taskCondition, int round) {
        String orderKey = DispatchConstant.REDIS_KEY_ORDER + order.getId();
        try {
            RedisLock.ins().lock(orderKey);
            Order newOrder = DispatchService.ins().getOrderById(order.getId());
            if (newOrder.getStatus() != DispatchConstant.ORDER_STATUS_ORDER_START) {
                return false;
            }
            // 循环查找车辆，2km、4km、6km
            for (Integer distance : taskCondition.getDistanceList()) {
                // 查找车辆集合
                List<DriverData> list = DispatchService.ins().getCarByOrder(order, taskCondition, distance, usedDriverId, round, true);
                if (list == null) {
                    status = STATUS_END;
                    return false;
                }
                log.info("INFO OrderForceTask - call car, orderId:{}, round:{}, driverCount:{}}", orderId, round, list.size());
                 for (DriverData data : list) { log.info("INFO OrderForceTask - car data, orderId:{}, round:{}, driverData:{}", orderId, round, JSONObject.parseObject(String.valueOf(data)));
                    Date startTime = new Date(order.getOrderStartTime().getTime() - TimeUnit.MINUTES.toMillis(taskCondition.getFreeTimeBefore()));
                    Date endTime = new Date(order.getOrderStartTime().getTime() + TimeUnit.MINUTES.toMillis(taskCondition.getFreeTimeAfter()));
                    String redisKey = DispatchConstant.REDIS_KEY_DRIVER + data.getDriverInfo().getId();
                    log.info("INFO OrderForceTask - car amap data, orderId:{}, round:{}, vehicleAmap:{}", orderId, round, JSONObject.parseObject(String.valueOf(data.getAmapVehicle())));
                    try {
                        RedisLock.ins().lock(redisKey);
                        // 可以接单
                        int count = DispatchService.ins().countDriverOrder(data.getDriverInfo().getId(), startTime, endTime);
                        if (count > 0) {
                            continue;
                        }
                        String otherPhone = order.getPassengerPhone();
                        if (StringUtils.isNotEmpty(order.getOtherPhone())) {
                            otherPhone = order.getOtherPhone();
                        }
                        OrderDto updateOrder = new OrderDto();
                        BoundPhoneDto bindAxbResponse1 = null;
                        BoundPhoneDto bindAxbResponse2 = null;
                        String otherMappingNumber = "";
                        String mappingNumber = "";
                        try {
                            if (StringUtils.isNotEmpty(order.getPassengerPhone())) {
                                // 调用file服务，绑定手机号
                                bindAxbResponse1 = DispatchService.ins().bindAxb(EncryptUtil.decryptionPhoneNumber(data.getDriverInfo().getPhoneNumber()), EncryptUtil.decryptionPhoneNumber(order.getPassengerPhone()), order.getOrderStartTime().getTime() + TimeUnit.DAYS.toMillis(1));
                                if (bindAxbResponse1 != null) {
                                    updateOrder.setMappingNumber(bindAxbResponse1.getAxbSecretNo());
                                    updateOrder.setMappingId(bindAxbResponse1.getAxbSubsId());
                                    mappingNumber = bindAxbResponse1.getAxbSecretNo();
                                }
                            }
                            if (StringUtils.isNotEmpty(order.getOtherPhone())) {
                                bindAxbResponse2 = DispatchService.ins().bindAxb(EncryptUtil.decryptionPhoneNumber(data.getDriverInfo().getPhoneNumber()), EncryptUtil.decryptionPhoneNumber(order.getOtherPhone()), order.getOrderStartTime().getTime() + TimeUnit.DAYS.toMillis(1));
                                if (bindAxbResponse2 != null) {
                                    updateOrder.setOtherMappingNumber(bindAxbResponse2.getAxbSecretNo());
                                    updateOrder.setOtherMappingId(bindAxbResponse2.getAxbSubsId());
                                    otherMappingNumber = bindAxbResponse2.getAxbSecretNo();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        updateOrder.setOrderId(orderId);
                        updateOrder.setId(orderId);
                        updateOrder.setPlateNumber(data.getCarInfo().getPlateNumber());
                        updateOrder.setDriverId(data.getDriverInfo().getId());
                        updateOrder.setDriverPhone(data.getDriverInfo().getPhoneNumber());
                        updateOrder.setStatus(DispatchConstant.ORDER_STATUS_DRIVER_ACCEPT);
                        updateOrder.setDriverStatus(DispatchConstant.ORDER_DRIVER_STATUS_ACCEPT);
                        updateOrder.setCarId(Integer.parseInt(data.getAmapVehicle().getVehicleId()));
                        updateOrder.setFakeSuccess(data.getIsFollowing());
                        updateOrder.setDriverGrabTime(new Date());
                        updateOrder.setUpdateType(DispatchConstant.ORDER_STATUS_ORDER_START);
                        // 更新订单状态
                        boolean success = DispatchService.ins().updateOrder(updateOrder);
                        if (success) {
                            // 更新高德
                            OrderRequest orderRequest = new OrderRequest();
                            orderRequest.setOrderId(order.getId() + "");
                            orderRequest.setCustomerDeviceId(order.getDeviceCode());
                            orderRequest.setType(order.getServiceType());
                            orderRequest.setStatus(2);
                            orderRequest.setOrderCity(orderRulePrice.getCityCode());
                            orderRequest.setVehicleId(order.getCarId() + "");
                            DispatchService.ins().updateAmapOrder(orderRequest);
                            status = STATUS_END;

                            String driverPhone = EncryptUtil.decryptionPhoneNumber(data.getDriverInfo().getPhoneNumber());
                            String passengerPhone = EncryptUtil.decryptionPhoneNumber(order.getOtherPhone() == null || order.getOtherPhone().isEmpty() ? order.getPassengerPhone() : order.getOtherPhone());
                            // 推送司机
                            pushDriver(order, orderRulePrice, data, passengerPhone);
                            // 推送乘客
                            pushPassanger(order, orderRulePrice, data, driverPhone);
                            // 短信司机
                            smsDriver(order, orderRulePrice, data, passengerPhone, driverPhone);
                            pushOther(order, orderRulePrice, data, StringUtils.isEmpty(otherMappingNumber) ? driverPhone : otherMappingNumber);
                            pushPassenger(order, orderRulePrice, data, StringUtils.isEmpty(mappingNumber) ? driverPhone : mappingNumber);
                            return true;
                        } else {
                            log.info("INFO OrderForceTask - farce task fail, orderId:{}", orderId);
                            if (bindAxbResponse2 != null) {
                                DispatchService.ins().unbind(bindAxbResponse2.getAxbSubsId(), bindAxbResponse2.getAxbSecretNo());
                            }
                            if (bindAxbResponse1 != null) {
                                DispatchService.ins().unbind(bindAxbResponse1.getAxbSubsId(), bindAxbResponse1.getAxbSecretNo());
                            }
                        }
                    } catch (Exception e) {
                        log.info("INFO OrderForceTask - farce send order fail, orderId:{}", orderId);
                    } finally {
                        log.info("INFO OrderForceTask - unlock redis key, redisKey:{}", redisKey);
                        RedisLock.ins().unlock(redisKey);
                    }
                }
            }
            return true;
        } finally {
            RedisLock.ins().unlock(orderKey);
        }
    }

    public void smsDriver(Order order, OrderRulePrice orderRulePrice, DriverData data, String passengerPhone, String driverPhone) {
        try {
            /*Map<String, Object> smsMap = new HashMap<>();
            smsMap.put("type", getTypeDesc(orderRulePrice.getServiceTypeId(), data.getIsFollowing()));
            smsMap.put("time", DateUtils.formatDate(order.getOrderStartTime(), DateUtils.yyyyMMddHHmm));
            smsMap.put("phone", StringUtils.substring(passengerPhone, passengerPhone.length() - 4));
            smsMap.put("start", order.getStartAddress());
            smsMap.put("end", order.getEndAddress());
            DispatchService.ins().sendSmsMessage(driverPhone, Const.SMS_FORCE_DISPATCH_DRIVER, smsMap);*/
            smsDriverHx(order, orderRulePrice, data, passengerPhone, driverPhone);
        } catch (Exception e) {
            log.error("#orderId= " + orderId + " smsDriver error", e);
        }
    }

    public void smsDriverHx(Order order, OrderRulePrice orderRulePrice, DriverData data, String passengerPhone, String driverPhone) {
        if (order.getServiceType() == OrderTypeEnum.CHARTERED_CAR_FULL.getCode() || order.getServiceType() == OrderTypeEnum.CHARTERED_CAR_HALF.getCode()) {
            DispatchService.ins().sendSmsMessageHx(driverPhone, DispatchConstant.HX_FORCE_DISPATCH_DRIVER_BAOCHE, getTypeDesc(orderRulePrice.getServiceTypeId(), data.getIsFollowing()), DateUtils.formatDate(order.getOrderStartTime(), DateUtils.yyMMddHHmm), order.getStartAddress(), StringUtils.substring(passengerPhone, passengerPhone.length() - 4));
        } else {
            DispatchService.ins().sendSmsMessageHx(driverPhone, DispatchConstant.HX_FORCE_DISPATCH_DRIVER, getTypeDesc(orderRulePrice.getServiceTypeId(), data.getIsFollowing()), DateUtils.formatDate(order.getOrderStartTime(), DateUtils.yyMMddHHmm), StringUtils.substring(passengerPhone, passengerPhone.length() - 4), order.getStartAddress(), order.getEndAddress());
        }
    }

    public void pushOther(Order order, OrderRulePrice orderRulePrice, DriverData driverData, String driverPhone) {
        try {

            if (order.getOrderType() != 2) {
                return;
            }
            PassengerInfo passengerInfo = DispatchService.ins().getPassengerInfo(order.getPassengerInfoId());
            String timeDesc = DateUtils.formatDate(order.getOrderStartTime(), DateUtils.yyMMddHHmm);
            String pname = passengerInfo.getPassengerName();
            String color = driverData.getCarInfo().getColor();
            String plateNumber = driverData.getCarInfo().getPlateNumber();
            // String desc = timeDesc + "从" + order.getStartAddress() + "到" + order.getEndAddress();

            String bookTime = "预定时间";
            String phone = EncryptUtil.decryptionPhoneNumber(order.getOtherPhone());

            DispatchService.ins().sendSmsMessageHx(phone, DispatchConstant.HX_FORCE_DISPATCH_PASSENGER, pname, color, plateNumber, StringUtils.substring(driverData.getDriverInfo().getDriverName(), 0, 1) + "师傅", driverPhone);
        } catch (Exception e) {
            log.error("ERROR OrderForceTask - push other fail");
        }
    }

    /**
     *
     * @param order
     * @param orderRulePrice
     * @param driverData
     * @param driverPhone
     */
    public void pushPassenger(Order order, OrderRulePrice orderRulePrice, DriverData driverData, String driverPhone) {
        try {
            // HX_FORCE_DISPATCH_PASSENGER2
            String name = StringUtils.substring(driverData.getDriverInfo().getDriverName(), 0, 1) + "师傅";
            String plateNumber = driverData.getCarInfo().getPlateNumber();
            // driverPhone
            String timeDesc = DateUtils.formatDate(order.getOrderStartTime(), DateUtils.yyMMddHHmm);
            String servicePhone = "0571-56030631";
            DispatchService.ins().sendSmsMessageHx(EncryptUtil.decryptionPhoneNumber(order.getPassengerPhone()), DispatchConstant.HX_FORCE_DISPATCH_PASSENGER2, name, plateNumber, driverPhone, servicePhone);
        } catch (Exception e) {
        }
    }

    public void pushPassanger(Order order, OrderRulePrice orderRulePrice, DriverData data, String driverPhone) {
        JSONObject msg = new JSONObject();
        msg.put("orderId", orderId);
        String contentMsg = "";
        try {
            CarInfo carInfo = data.getCarInfo();
            msg.put("plateNumber", carInfo.getPlateNumber());
            msg.put("brand", data.getCarInfo().getFullName());
            msg.put("color", data.getCarInfo().getColor());
            String driverPhoneNumber = data.getDriverInfo().getPhoneNumber();
            msg.put("driverName", data.getDriverInfo().getDriverName());
            msg.put("driverPhoneNum", StringUtils.substring(driverPhone, driverPhone.length() - 4));
            msg.put("driverHeadImg", data.getDriverInfo().getHeadImg());
            msg.put("mappingNumber", EncryptUtil.decryptionPhoneNumber(driverPhoneNumber));
            msg.put("avgGrade", DispatchService.ins().getDriverEvaluateByDriverId(data.getDriverInfo().getId()));
            msg.put("carImg", carInfo.getCarImg());
            msg.put("driverLng", data.getAmapVehicle().getLongitude());
            msg.put("driverLat", data.getAmapVehicle().getLatitude());
            msg.put("messageType", MessageType.PASSENGER_SEND_ORDER);
            log.info("#orderId= " + orderId + "  round = " + round + "  派单推送 pushRequest = " + msg);
            contentMsg = "尊敬的逸品出行用户,您" + DateUtils.formatDate(order.getOrderStartTime(), DateUtils.yyMMddHHmm) + "的订单已指派给" + StringUtils.substring(data.getDriverInfo().getDriverName(), 0, 1) + "师傅,车牌号:" + data.getCarInfo().getPlateNumber() + ",车身颜色:" + data.getCarInfo().getColor();
        } catch (Exception e) {
            log.info("强派向乘客推送消息,组装消息异常");
        } finally {
            PushRequest pushRequest = new PushRequest();
            pushRequest.setSendId(order.getDriverId() + "");
            pushRequest.setSendIdentity(IdentityEnum.DRIVER.getCode());
            pushRequest.setAcceptIdentity(IdentityEnum.PASSENGER.getCode());
            pushRequest.setAcceptId(order.getPassengerInfoId() + "");
            pushRequest.setMessageType(MessageType.PASSENGER_SEND_ORDER);
            pushRequest.setTitle("派单");
            pushRequest.setMessageBody(msg.toString());
            pushRequest.setBusinessMessage(contentMsg);
            pushRequest.setBusinessType(DispatchConstant.BUSINESS_MESSAGE_TYPE_ORDER);
            DispatchService.ins().pushMessage(pushRequest);
        }
    }

    /**
     * 为司机派送订单
     *
     * @param order
     * @param orderRulePrice
     * @param data
     * @param passengerPhone
     */
    public void pushDriver(Order order, OrderRulePrice orderRulePrice, DriverData data, String passengerPhone) {
        try {
            String timeDesc = DateUtils.formatDate(order.getOrderStartTime(), DateUtils.yyMMddHHmm);
            JSONObject msg = new JSONObject();
            DecimalFormat df = new DecimalFormat("#0.00");
            String typeDesc = "";
            String useFeature = order.getUserFeature();
            String content = "";
            if (order.getServiceType() == OrderTypeEnum.CHARTERED_CAR_HALF.getCode() || order.getServiceType() == OrderTypeEnum.CHARTERED_CAR_FULL.getCode()) {
                content = "您收到一条包车派单," + timeDesc + "上车点" + order.getStartAddress() + "乘客尾号" + StringUtils.substring(passengerPhone, passengerPhone.length() - 4) + ",请合理安排接乘时间";
            } else {
                content = "您收到一条" + getTypeDesc(orderRulePrice.getServiceTypeId(), data.getIsFollowing()) + "," + timeDesc + "乘客尾号" + StringUtils.substring(passengerPhone, passengerPhone.length() - 4) + ",从" + order.getStartAddress() + "到" + order.getEndAddress() + "的订单,请合理安排接乘时间";
            }
            msg.put("content", content);
            msg.put("messageType", 4004);
            msg.put("orderId", order.getId());
            double startAddressDistance = DispatchService.ins().calDistance(data.getAmapVehicle().getLongitude(), data.getAmapVehicle().getLatitude(), order.getStartLongitude(), order.getStartLatitude());
            msg.put("startAddressDistance", df.format(startAddressDistance / 1000));
            msg.put("totalPrice", df.format(orderRulePrice.getTotalPrice()));
            msg.put("totalDistance", df.format(orderRulePrice.getTotalDistance()));
            msg.put("startTime", timeDesc);
            msg.put("startAddress", order.getStartAddress());
            msg.put("endAddress", order.getEndAddress());
            msg.put("isFollowing", data.getIsFollowing());
            msg.put("serviceType", orderRulePrice.getServiceTypeId());
            msg.put("userFeature", order.getUserFeature());

            ChargeRule chargeRule = DispatchService.ins().getChargeRule(orderRulePrice.getCityCode(), orderRulePrice.getServiceTypeId(), orderRulePrice.getCarLevelId());
            msg.put("charterCarInfo", DispatchService.ins().getChargeRuleStr(chargeRule));
            msg.put("time", chargeRule == null ? 0 : chargeRule.getBaseMinutes() / 60);
            if (order.getServiceType() == OrderTypeEnum.CHARTERED_CAR_FULL.getCode()) {
                msg.put("forecastTime", 8);
            }
            if (order.getServiceType() == OrderTypeEnum.CHARTERED_CAR_HALF.getCode()) {
                msg.put("forecastTime", 4);
            }
            msg.put("tagList", getTagsJson(order.getUserFeature()));

            PushRequest pushRequest = new PushRequest();
            pushRequest.setSendId(order.getPassengerInfoId() + "");
            pushRequest.setSendIdentity(IdentityEnum.PASSENGER.getCode());
            pushRequest.setAcceptIdentity(IdentityEnum.DRIVER.getCode());
            pushRequest.setAcceptId(data.getDriverInfo().getId() + "");
            pushRequest.setMessageType(MessageType.ORDER_SEND_ORDER);
            pushRequest.setTitle("派单");
            pushRequest.setMessageBody(msg.toString());
            pushRequest.setBusinessMessage(content);
            pushRequest.setBusinessType(DispatchConstant.BUSINESS_MESSAGE_TYPE_ORDER);
            log.info("#orderId= " + orderId + "  round = " + round + "  派单推送 pushRequest = " + pushRequest);
            DispatchService.ins().pushMessage(pushRequest);
        } catch (Exception e) {
            log.error("#orderId= " + orderId + " pushDriver error", e);
        }
    }

    @Override
    public void taskEnd(Order order, OrderRulePrice orderRulePrice) {
        log.info("INFO OrderForceTask - force order end, orderId:{}", order.getId());
    }
}