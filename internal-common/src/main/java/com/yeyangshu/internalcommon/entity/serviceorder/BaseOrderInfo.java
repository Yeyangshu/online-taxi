package com.yeyangshu.internalcommon.entity.serviceorder;

import lombok.Data;

/**
 * 订单基础信息
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 21:53
 */
@Data
public class BaseOrderInfo {

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 服务名称
     */
    private String serviceTypeName;

    /**
     * 车辆级别名称
     */
    private String carLevelName;
}
