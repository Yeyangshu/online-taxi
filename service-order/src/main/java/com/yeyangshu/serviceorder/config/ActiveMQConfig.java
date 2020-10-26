package com.yeyangshu.serviceorder.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;

/**
 * 生产者
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/10/26 22:21
 */
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("ActiveMQQueue");
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(brokerUrl);
    }

}
