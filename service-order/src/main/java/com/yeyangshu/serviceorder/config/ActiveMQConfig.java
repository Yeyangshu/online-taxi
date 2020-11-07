package com.yeyangshu.serviceorder.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

/**
 * 消息消费端配置类
 */
@Configuration
public class ActiveMQConfig {

    /**
     * ActiveMQ地址
     */
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    /**
     * 连接工厂
     *
     * @return
     */
    @Bean
    public ActiveMQConnectionFactory connectionFactory(RedeliveryPolicy redeliveryPolicy) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        return activeMQConnectionFactory;
    }

    /**
     * 重发配置
     *
     * @return
     */
    @Bean
    public RedeliveryPolicy redeliveryPolicy() {
        return new RedeliveryPolicy();
    }

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMQConnectionFactory);
        // 1: 自动确认，2： 客户端手动确认，3：自动批量确认，4 事务提交并确认。
        bean.setSessionAcknowledgeMode(2);
        return bean;
    }

}