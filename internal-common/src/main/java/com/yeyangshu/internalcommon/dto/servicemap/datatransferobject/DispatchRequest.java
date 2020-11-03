package com.yeyangshu.internalcommon.dto.servicemap.datatransferobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 派单信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispatchRequest {

    private String orderId;

    private String customerDeviceId;

    private Integer orderType;

    private Integer vehicleType;

    private String orderCity;

    private Long orderTime;

    private Long startTime;

    private String startName;

    private String startLongitude;

    private String startLatitude;

    private String endName;

    private String endLongitude;

    private String endLatitude;

    private Integer radius;

    private Integer maxCount;
}