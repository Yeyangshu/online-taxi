package com.yeyangshu.internalcommon.dto.servicepassengeruser;

import lombok.Data;

import java.util.Date;

/**
 * 乘客登陆来源
 * <tt>tbl_passenger_register_source</tt>
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/31 13:48
 */
@Data
public class PassengerRegisterSource {

    /**
     * 数据库主键id
     * This field corresponds to the database column tbl_passenger_register_source.id
     */
    private Integer id;

    /**
     * 乘客信息主键
     * This field corresponds to the database column tbl_passenger_register_source.passenger_info_id
     */
    private Integer passengerInfoId;

    /**
     * 注册来源
     * This field corresponds to the database column tbl_passenger_register_source.register_source
     */
    private String registerSource;

    /**
     * 市场渠道
     * This field corresponds to the database column tbl_passenger_register_source.market_channel
     */
    private String marketChannel;

    /**
     * 创建时间
     * This field corresponds to the database column tbl_passenger_register_source.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * This field corresponds to the database column tbl_passenger_register_source.update_time
     */
    private Date updateTime;
}
