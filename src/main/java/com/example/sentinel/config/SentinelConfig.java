package com.example.sentinel.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sentinel配置类
 * 
 * @author Example
 * @since 1.0.0
 */
@Configuration
public class SentinelConfig {
    //注解方式定义资源和blockerHandler比较舒服！

    /**
     * 配置Sentinel注解切面
     * 用于支持@SentinelResource注解
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();//一定要有这个bean，不然注解不会生效！
    }
}

