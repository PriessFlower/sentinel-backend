package com.example.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Sentinel学习控制器
 * 演示各种Sentinel功能
 * 
 * @author Example
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/sentinel")
public class SentinelController {


    //处理方法比资源方法的参数多一个异常参数，其他都是一样的！
    /**
     * 基础限流示例
     * 使用@SentinelResource注解进行资源保护
     */
    @GetMapping("/basic")
    @SentinelResource(value = "basic-resource", blockHandler = "basicBlockHandler")
    public String basicFlowControl() {
        log.info("执行基础限流示例");
        return "基础限流示例执行成功";
    }

    /**
     * 基础限流的降级处理方法
     */
    public String basicBlockHandler(BlockException ex) {
        log.warn("基础限流触发: {}", ex.getMessage());
        return "请求被限流，请稍后重试";
    }

    /**
     * 带参数的限流示例
     */
    @GetMapping("/param")
    @SentinelResource(value = "param-resource", blockHandler = "paramBlockHandler")
    public String paramFlowControl(@RequestParam String name) {
        log.info("执行带参数限流示例，参数: {}", name);
        return "Hello " + name + "，带参数限流示例执行成功";
    }

    /**
     * 带参数限流的降级处理方法
     */
    public String paramBlockHandler(String name, BlockException ex) {
        log.warn("带参数限流触发，参数: {}, 异常: {}", name, ex.getMessage());
        return "Hello " + name + "，请求被限流，请稍后重试";
    }

    /**
     * 模拟慢调用示例
     */
    @GetMapping("/slow")
    @SentinelResource(value = "slow-resource", blockHandler = "slowBlockHandler")
    public String slowCall() {
        log.info("执行慢调用示例");
        try {
            // 模拟慢调用
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "慢调用示例执行成功";
    }

    /**
     * 慢调用的降级处理方法
     */
    public String slowBlockHandler(BlockException ex) {
        log.warn("慢调用限流触发: {}", ex.getMessage());
        return "慢调用被限流，请稍后重试";
    }

    //触发业务异常不会算在拒绝qbs里
    //而且我们一般不会主动抛出block异常，我们主动可以抛出业务异常，block异常由sentinel系统抛出（根据资源规则）
    /**
     * 异常比例示例
     */
    @GetMapping("/exception")
    @SentinelResource(value = "exception-resource", blockHandler = "exceptionBlockHandler")
    public String exceptionRatio() {
        log.info("执行异常比例示例");
        // 模拟随机异常
        if (Math.random() > 0.7) {
            //当异常比例大于某个临界值的时候，快速失败！
            throw new RuntimeException("模拟异常");
        }
        return "异常比例示例执行成功";
    }

    /**
     * 异常比例的降级处理方法
     */
    public String exceptionBlockHandler(BlockException ex) {
        log.warn("异常比例限流触发: {}", ex.getMessage());
        return "异常比例过高，请求被限流";
    }

    /**
     * 热点参数限流示例
     */
    @GetMapping("/hot")
    @SentinelResource(value = "hot-resource", blockHandler = "hotBlockHandler")
    public String hotParam(@RequestParam(required = false) String userId,
                          @RequestParam(required = false) String itemId) {
        log.info("执行热点参数限流示例，userId: {}, itemId: {}", userId, itemId);
        return "热点参数限流示例执行成功，userId: " + userId + ", itemId: " + itemId;
    }

    /**
     * 热点参数限流的降级处理方法
     */
    public String hotBlockHandler(String userId, String itemId, BlockException ex) {
        log.warn("热点参数限流触发，userId: {}, itemId: {}, 异常: {}", userId, itemId, ex.getMessage());
        return "热点参数请求被限流，请稍后重试";
    }

    /**
     * 系统自适应限流示例
     */
    @GetMapping("/system")
    @SentinelResource(value = "system-resource", blockHandler = "systemBlockHandler")
    public String systemAdaptive() {
        log.info("执行系统自适应限流示例");
        return "系统自适应限流示例执行成功";
    }

    /**
     * 系统自适应限流的降级处理方法
     */
    public String systemBlockHandler(BlockException ex) {
        log.warn("系统自适应限流触发: {}", ex.getMessage());
        return "系统负载过高，请求被限流";
    }
}

