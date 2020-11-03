package com.yeyangshu.internalcommon.dto.serviceorderdispatch.datatransferobject;

import com.yeyangshu.internalcommon.dto.serviceorder.dataobject.Order;
import lombok.Data;

/**
 * 订单信息接受类
 */
@Data
public class OrderDto extends Order {

    private Integer orderId;

    private Integer updateType;
}