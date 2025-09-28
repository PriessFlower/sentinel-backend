package com.example.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Sentinel学习服务类
 * 演示在Service层使用Sentinel
 * 
 * @author Example
 * @since 1.0.0
 */
@Slf4j
@Service
public class SentinelService {

    /**
     * 模拟数据库查询
     */
    @SentinelResource(value = "database-query", blockHandler = "databaseQueryBlockHandler")
    public String queryDatabase(String tableName) {
        log.info("查询数据库表: {}", tableName);
        // 模拟数据库查询耗时
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "查询表 " + tableName + " 成功，返回100条记录";
    }

    /**
     * 数据库查询的降级处理方法
     */
    public String databaseQueryBlockHandler(String tableName, BlockException ex) {
        log.warn("数据库查询被限流，表名: {}, 异常: {}", tableName, ex.getMessage());
        return "数据库查询被限流，请稍后重试";
    }

    /**
     * 模拟外部API调用
     */
    @SentinelResource(value = "external-api", blockHandler = "externalApiBlockHandler")
    public String callExternalApi(String apiName) {
        log.info("调用外部API: {}", apiName);
        // 模拟外部API调用
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "调用外部API " + apiName + " 成功";
    }

    /**
     * 外部API调用的降级处理方法
     */
    public String externalApiBlockHandler(String apiName, BlockException ex) {
        log.warn("外部API调用被限流，API名称: {}, 异常: {}", apiName, ex.getMessage());
        return "外部API调用被限流，请稍后重试";
    }

    /**
     * 模拟文件处理
     */
    @SentinelResource(value = "file-process", blockHandler = "fileProcessBlockHandler")
    public String processFile(String fileName) {
        log.info("处理文件: {}", fileName);
        // 模拟文件处理
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "文件 " + fileName + " 处理成功";
    }

    /**
     * 文件处理的降级处理方法
     */
    public String fileProcessBlockHandler(String fileName, BlockException ex) {
        log.warn("文件处理被限流，文件名: {}, 异常: {}", fileName, ex.getMessage());
        return "文件处理被限流，请稍后重试";
    }

    /**
     * 模拟缓存操作
     */
    @SentinelResource(value = "cache-operation", blockHandler = "cacheOperationBlockHandler")
    public String cacheOperation(String key) {
        log.info("缓存操作，键: {}", key);
        // 模拟缓存操作
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "缓存操作成功，键: " + key;
    }

    /**
     * 缓存操作的降级处理方法
     */
    public String cacheOperationBlockHandler(String key, BlockException ex) {
        log.warn("缓存操作被限流，键: {}, 异常: {}", key, ex.getMessage());
        return "缓存操作被限流，请稍后重试";
    }
}

