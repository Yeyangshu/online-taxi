package com.yeyangshu.internalcommon.entity.serviceorderdispatch.datatransferobject;

import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.Order;
import lombok.Data;

/**
 * 订单信息接受类
 */
@Data
public class OrderDto extends Order {

    private Integer orderId;

    private Integer updateType;
}