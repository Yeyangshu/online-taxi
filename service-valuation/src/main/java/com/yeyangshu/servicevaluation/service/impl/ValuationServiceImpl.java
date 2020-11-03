package com.yeyangshu.servicevaluation.service.impl;

import com.yeyangshu.internalcommon.entity.ResponseResult;
import com.yeyangshu.internalcommon.entity.servicevaluation.dataobject.BasicRule;
import com.yeyangshu.internalcommon.entity.servicevaluation.dataobject.ChargeRule;
import com.yeyangshu.internalcommon.entity.servicevaluation.dataobject.Rule;
import com.yeyangshu.servicevaluation.service.ValuationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 计价服务实现类
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/11/1 19:20
 */
@Service
@Slf4j
public class ValuationServiceImpl implements ValuationService {

    /**
     * 计价规则
     *
     * @param request 预估价格信息类
     * @return
     * @throws Exception
     */
    @Override
    public ResponseResult valuationRule(OrderRequest request) throws Exception {
        log.info("valuation rule request:" + request);
        Rule rule = new Rule();

        // 数据库取取计价规则，暂时忽略
        ChargeRule chargeRule = new ChargeRule();
        chargeRule.setCityCode(request.getCityCode());
        chargeRule.setServiceTypeId(request.getServiceTypeId());
        chargeRule.setChannelId(request.getChannelId());
        chargeRule.setCarLevelId(request.getCarLevelId());

        BasicRule basicRule = new BasicRule();
        basicRule.setLowestPrice(BigDecimal.valueOf(10.00));
        basicRule.setBasePrice(BigDecimal.valueOf(10.00));
        basicRule.setKilos(5.00);
        basicRule.setMinutes(15.00);
        rule.setBasicRule(basicRule);
        rule.setId(1);
        return ResponseResult.success(rule);
    }
}