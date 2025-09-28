package com.example.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sentinel学习项目主启动类
 * 
 * @author Example
 * @since 1.0.0
 */
@SpringBootApplication
public class SentinelLearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelLearningApplication.class, args);
        System.out.println("Sentinel学习项目启动成功！");
        System.out.println("访问地址: http://localhost:8080");
        System.out.println("Sentinel控制台: http://localhost:8080/actuator/sentinel");
    }
}

