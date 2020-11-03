package com.yeyangshu.serviceorderdispatch.mapper;


import com.yeyangshu.internalcommon.dto.servicevaluation.ChargeRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChargeRuleMapper {
    ChargeRule getChargeRule(@Param("cityCode") String cityCode, @Param("serviceTypeId") int serviceTypeId, @Param("carLevel") int carLevel);
}