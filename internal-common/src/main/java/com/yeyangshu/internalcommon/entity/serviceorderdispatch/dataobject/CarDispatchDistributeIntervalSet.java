package com.yeyangshu.internalcommon.entity.serviceorderdispatch.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 车辆调度派单间隔设置
 * A data object class directly models database table <tt>tbl_car_dispatch_distribute_interval_set</tt>.
 */
@Data
public class CarDispatchDistributeIntervalSet {

    private Integer id;

    private String cityCode;

    private Integer serviceTypeId;

    private Integer carServiceBeforeInterval;

    private Integer carServiceAfterInterval;

    private Date createTime;

    private Date updateTime;
}