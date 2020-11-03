package com.yeyangshu.serviceorder.mapper;

import com.yeyangshu.internalcommon.entity.apipassenger.datatransferobject.OrderRequest;
import com.yeyangshu.internalcommon.entity.serviceorder.dataobject.Order;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 */
@Service
public interface OrderMapper {

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(OrderRequest record);

    int batchUpdate(Map<String, Object> map);

}