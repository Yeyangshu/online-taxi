package com.yeyangshu.internalcommon.entity.serviceorderdispatch.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 车辆调度派发设置
 * A data object class directly models database table <tt>tbl_car_dispatch_distribute_set</tt>.
 */
@Data
public class CarDispatchDistributeSet {

    private Integer id;

    private String cityCode;

    private Boolean isForceDistribute;

    private Date createTime;

    private Date updateTime;
}