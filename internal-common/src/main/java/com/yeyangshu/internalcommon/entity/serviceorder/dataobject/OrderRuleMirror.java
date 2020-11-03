package com.yeyangshu.internalcommon.entity.serviceorder.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * 订单计费规则镜像表
 */
@Data
public class OrderRuleMirror {

    /**
     * 数据库主键id
     * This field corresponds to the database column tbl_order_rule_mirror.id
     */
    private Integer id;

    /**
     * 订单_id
     * This field corresponds to the database column tbl_order_rule_mirror.order_id
     */
    private Integer orderId;

    /**
     * 计价规则id
     * This field corresponds to the database column tbl_order_rule_mirror.rule_id
     */
    private Integer ruleId;

    /**
     * 规则镜像的json
     * This field corresponds to the database column tbl_order_rule_mirror.rule
     */
    private String rule;

    /**
     * 创建时间
     * This field corresponds to the database column tbl_order_rule_mirror.create_time
     */
    private Date createTime;

    /**
     * 更新时间
     * This field corresponds to the database column tbl_order_rule_mirror.update_time
     */
    private Date updateTime;

}