package com.yeyangshu.internalcommon.dto.serviceorder.dataobject;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    /**
     * This field corresponds to the database column tbl_order.id
     */
    private Integer id;

    /**
     * This field corresponds to the database column tbl_order.order_number
     */
    private String orderNumber;

    /**
     * This field corresponds to the database column tbl_order.passenger_info_id
     */
    private Integer passengerInfoId;

    /**
     * This field corresponds to the database column tbl_order.passenger_phone
     */
    private String passengerPhone;

    /**
     * This field corresponds to the database column tbl_order.device_code
     */
    private String deviceCode;

    /**
     * This field corresponds to the database column tbl_order.driver_id
     */
    private Integer driverId;

    /**
     * This field corresponds to the database column tbl_order.driver_status
     */
    private Integer driverStatus;

    /**
     * This field corresponds to the database column tbl_order.driver_phone
     */
    private String driverPhone;

    /**
     * This field corresponds to the database column tbl_order.car_id
     */
    private Integer carId;

    /**
     * This field corresponds to the database column tbl_order.plate_number
     */
    private String plateNumber;

    /**
     * This field corresponds to the database column tbl_order.user_longitude
     */
    private String userLongitude;

    /**
     * This field corresponds to the database column tbl_order.user_latitude
     */
    private String userLatitude;

    /**
     * This field corresponds to the database column tbl_order.start_longitude
     */
    private String startLongitude;

    /**
     * This field corresponds to the database column tbl_order.start_latitude
     */
    private String startLatitude;

    /**
     * This field corresponds to the database column tbl_order.start_address
     */
    private String startAddress;

    /**
     * This field corresponds to the database column tbl_order.end_address
     */
    private String endAddress;

    /**
     * This field corresponds to the database column tbl_order.start_time
     */
    private Date startTime;

    /**
     * This field corresponds to the database column tbl_order.order_start_time
     */
    private Date orderStartTime;

    /**
     * This field corresponds to the database column tbl_order.end_longitude
     */
    private String endLongitude;

    /**
     * This field corresponds to the database column tbl_order.end_latitude
     */
    private String endLatitude;

    /**
     * This field corresponds to the database column tbl_order.driver_grab_time
     */
    private Date driverGrabTime;

    /**
     * This field corresponds to the database column tbl_order.driver_start_time
     */
    private Date driverStartTime;

    /**
     * This field corresponds to the database column tbl_order.driver_arrived_time
     */
    private Date driverArrivedTime;

    /**
     * This field corresponds to the database column tbl_order.pick_up_passenger_time
     */
    private Date pickUpPassengerTime;

    /**
     * This field corresponds to the database column tbl_order.pick_up_passenger_longitude
     */
    private String pickUpPassengerLongitude;

    /**
     * This field corresponds to the database column tbl_order.pick_up_passenger_latitude
     */
    private String pickUpPassengerLatitude;

    /**
     * This field corresponds to the database column tbl_order.pick_up_passenger_address
     */
    private String pickUpPassengerAddress;

    /**
     * This field corresponds to the database column tbl_order.receive_passenger_time
     */
    private Date receivePassengerTime;

    /**
     * This field corresponds to the database column tbl_order.receive_passenger_longitude
     */
    private String receivePassengerLongitude;

    /**
     * This field corresponds to the database column tbl_order.receive_passenger_latitude
     */
    private String receivePassengerLatitude;

    /**
     * This field corresponds to the database column tbl_order.receive_passenger_address
     */
    private String receivePassengerAddress;

    /**
     * This field corresponds to the database column tbl_order.passenger_getoff_time
     */
    private Date passengerGetoffTime;

    /**
     * This field corresponds to the database column tbl_order.passenger_getoff_longitude
     */
    private String passengerGetoffLongitude;

    /**
     * This field corresponds to the database column tbl_order.passenger_getoff_latitude
     */
    private String passengerGetoffLatitude;

    /**
     * This field corresponds to the database column tbl_order.passenger_getoff_address
     */
    private String passengerGetoffAddress;

    /**
     * This field corresponds to the database column tbl_order.other_name
     */
    private String otherName;

    /**
     * This field corresponds to the database column tbl_order.other_phone
     */
    private String otherPhone;

    /**
     * This field corresponds to the database column tbl_order.order_type
     */
    private Integer orderType;

    /**
     * This field corresponds to the database column tbl_order.service_type
     */
    private Integer serviceType;

    /**
     * This field corresponds to the database column tbl_order.order_channel
     */
    private Integer orderChannel;

    /**
     * This field corresponds to the database column tbl_order.status
     */
    private Integer status;

    /**
     * This field corresponds to the database column tbl_order.user_feature
     */
    private String userFeature;

    /**
     * This field corresponds to the database column tbl_order.transaction_id
     */
    private String transactionId;

    /**
     * This field corresponds to the database column tbl_order.mapping_id
     */
    private String mappingId;

    /**
     * This field corresponds to the database column tbl_order.mapping_number
     */
    private String mappingNumber;

    /**
     * This field corresponds to the database column tbl_order.other_mapping_id
     */
    private String otherMappingId;

    /**
     * This field corresponds to the database column tbl_order.other_mapping_number
     */
    private String otherMappingNumber;

    /**
     * This field corresponds to the database column tbl_order.merchant_id
     */
    private String merchantId;

    /**
     * This field corresponds to the database column tbl_order.is_evaluate_driver
     */
    private Integer evaluateDriver;

    /**
     * This field corresponds to the database column tbl_order.is_evaluate
     */
    private Integer evaluate;

    /**
     * This field corresponds to the database column tbl_order.invoice_type
     */
    private Integer invoiceType;

    /**
     * This field corresponds to the database column tbl_order.is_annotate
     */
    private Integer annotate;

    /**
     * This field corresponds to the database column tbl_order.source
     */
    private String source;

    /**
     * This field corresponds to the database column tbl_order.use_coupon
     */
    private Integer useCoupon;

    /**

     * This field corresponds to the database column tbl_order.cancel_order_type

     */
    private Integer cancelOrderType;

    /**

     * This field corresponds to the database column tbl_order.pay_type

     */
    private Integer payType;

    /**
     * This field corresponds to the database column tbl_order.is_paid
     */
    private Integer paid;

    /**
     * This field corresponds to the database column tbl_order.is_cancel
     */
    private Integer cancel;

    /**
     * This field corresponds to the database column tbl_order.is_adjust
     */
    private Integer adjust;

    /**
     * This field corresponds to the database column tbl_order.is_dissent
     */
    private Integer dissent;

    /**
     * This field corresponds to the database column tbl_order.is_manual
     */
    private Integer manual;

    /**
     * This field corresponds to the database column tbl_order.is_following
     */
    private Integer following;

    /**
     * This field corresponds to the database column tbl_order.is_fake_success
     */
    private Integer fakeSuccess;

    /**
     * This field corresponds to the database column tbl_order.memo
     */
    private String memo;

    /**
     * This field corresponds to the database column tbl_order.is_use_risk
     */
    private Integer useRisk;

    /**
     * This field corresponds to the database column tbl_order.create_time
     */
    private Date createTime;

    /**
     * This field corresponds to the database column tbl_order.update_time
     */
    private Date updateTime;

}