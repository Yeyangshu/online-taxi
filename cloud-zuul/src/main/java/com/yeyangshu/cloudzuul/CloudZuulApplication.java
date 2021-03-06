package com.yeyangshu.cloudzuul;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
public class CloudZuulApplication {

    public static void main(String[] args) {
        init();
        SpringApplication.run(CloudZuulApplication.class, args);
    }

    /**
     * sentinel生成令牌
     */
    private static void init() {
        // 所有限流规则的合集
        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();
        // 资源名称
        rule.setResource("HelloWorld");
        // 降级模式
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // QPS阈值
        rule.setCount(2);

        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

}
