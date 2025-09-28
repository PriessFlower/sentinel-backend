package com.example.sentinel.controller;

import com.example.sentinel.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务层Sentinel示例控制器
 * 
 * @author Example
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private SentinelService sentinelService;

    /**
     * 数据库查询示例
     */
    @GetMapping("/database")
    public String databaseQuery(@RequestParam(defaultValue = "users") String tableName) {
        log.info("请求数据库查询，表名: {}", tableName);
        return sentinelService.queryDatabase(tableName);
    }

    /**
     * 外部API调用示例
     */
    @GetMapping("/api")
    public String externalApi(@RequestParam(defaultValue = "user-service") String apiName) {
        log.info("请求外部API调用，API名称: {}", apiName);
        return sentinelService.callExternalApi(apiName);
    }

    /**
     * 文件处理示例
     */
    @GetMapping("/file")
    public String fileProcess(@RequestParam(defaultValue = "test.txt") String fileName) {
        log.info("请求文件处理，文件名: {}", fileName);
        return sentinelService.processFile(fileName);
    }

    /**
     * 缓存操作示例
     */
    @GetMapping("/cache")
    public String cacheOperation(@RequestParam(defaultValue = "user:123") String key) {
        log.info("请求缓存操作，键: {}", key);
        return sentinelService.cacheOperation(key);
    }
}

