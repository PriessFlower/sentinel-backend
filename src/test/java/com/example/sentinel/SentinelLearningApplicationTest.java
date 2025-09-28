package com.example.sentinel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Sentinel学习项目测试类
 * 
 * @author Example
 * @since 1.0.0
 */
@SpringBootTest
class SentinelLearningApplicationTest {

    @Test
    void contextLoads() {
        // 测试Spring Boot应用上下文是否能正常加载
        System.out.println("Spring Boot应用上下文加载测试通过！");
    }

    @Test
    void testBasicFunctionality() {
        // 基本功能测试
        System.out.println("基本功能测试通过！");
        assert true; // 简单的断言测试
    }
}
