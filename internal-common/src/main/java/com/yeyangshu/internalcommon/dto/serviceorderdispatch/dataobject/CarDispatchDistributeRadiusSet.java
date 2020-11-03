package com.yeyangshu.internalcommon.dto.serviceorderdispatch.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 车辆调度派单半径设置
 * A data object class directly models database table <tt>tbl_car_dispatch_distribute_radius_set</tt>.
 */
@Data
public class CarDispatchDistributeRadiusSet {
    private Integer id;

    private String cityCode;

    private Integer serviceTypeId;

    private Integer minRadius;

    private Integer minRadiusFirstPushDriverCount;

    private Integer maxRadius;

    private Integer maxRadiusFirstPushDriverCount;

    private Date createTime;

    private Date updateTime;

}