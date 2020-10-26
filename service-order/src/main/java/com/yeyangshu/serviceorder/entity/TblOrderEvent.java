package com.yeyangshu.serviceorder.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/26 22:23
 */
@Data
public class TblOrderEvent {
    private Integer id;

    /**
     * 事件类型（支付表支付完成，订单表修改状态）
     */
    private String orderType;

    /**
     * 事件环节（new,published,processed)
     */
    private String process;

    /**
     * 事件内容，保存事件发生时需要传递的数据
     */
    private String content;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
