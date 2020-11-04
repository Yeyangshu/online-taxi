package com.yeyangshu.serviceorderdispatch.config;

import com.yeyangshu.serviceorderdispatch.lock.RedisLock;
import com.yeyangshu.serviceorderdispatch.service.DispatchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 单例模式解决定时任务无法自动注册service的问题
 */
@Configuration
public class BeanConfig {

    @Bean
    public DispatchService dispatchService() {
        return DispatchService.ins();
    }

    @Bean
    public RedisLock redisLock() {
        return RedisLock.ins();
    }
}