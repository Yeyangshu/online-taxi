package com.yeyangshu.internalcommon.entity.servicepay.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息事件表
 * <tt>tbl_order_event</tt>
 */
@Data
public class OrderEvent implements Serializable {

    private static final long serialVersionUID = -8995362739473219653L;

    /**
     * 数据库主键id
     */
    private Integer id;

    /**
     * 事件类型（支付表支付完成，订单表修改状态）
     */
    private String orderType;

    /**
     * 事件环节（new, published, processed)
     */
    private String process;

    /**
     * 事件内容，保存事件发生时需要传递的数据
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}