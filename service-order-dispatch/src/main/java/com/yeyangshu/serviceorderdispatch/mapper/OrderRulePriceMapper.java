package com.yeyangshu.serviceorderdispatch.mapper;

import com.yeyangshu.internalcommon.dto.serviceorder.OrderRulePrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface OrderRulePriceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OrderRulePrice record);

    int insertSelective(OrderRulePrice record);

    OrderRulePrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRulePrice record);

    int updateByPrimaryKey(OrderRulePrice record);

    OrderRulePrice selectByOrderId(@Param("orderId") int orderId);

}