package com.yeyangshu.internalcommon.dto.servicemap.datatransferobject;

import lombok.Data;

import java.util.List;

/**
 * 派单车辆
 */
@Data
public class Dispatch {

    private Integer count;

    private String orderId;

    private List<Vehicle> vehicles;
}