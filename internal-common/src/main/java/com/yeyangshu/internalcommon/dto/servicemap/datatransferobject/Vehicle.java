package com.yeyangshu.internalcommon.dto.servicemap.datatransferobject;

import lombok.Data;

/**
 * 车辆信息
 */
@Data
public class Vehicle {

    /**
     * 车辆id
     */
    private String vehicleId;

    /**
     * 车辆精度
     */
    private String longitude;

    /**
     * 车辆纬度
     */
    private String latitude;

    /**
     * 里程
     */
    private String distance;

}