# Sentinel学习项目部署文档

## 项目概述

本项目是一个基于Spring Boot的Sentinel学习项目，演示了Sentinel的各种功能，包括：
- 基础限流
- 带参数限流
- 慢调用熔断
- 异常比例熔断
- 热点参数限流
- 系统自适应限流

## 环境要求

- Java 21+
- Maven 3.6+
- Windows PowerShell 或 Command Prompt

## 项目结构

```
sentinel-learning-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/sentinel/
│   │   │       ├── SentinelLearningApplication.java
│   │   │       ├── controller/
│   │   │       │   └── SentinelController.java
│   │   │       └── config/
│   │   │           └── SentinelConfig.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── static/
│   │           └── index.html
├── target/
├── pom.xml
└── sentinel-dashboard-1.8.6.jar
```

## 部署步骤

### 1. 启动Sentinel Dashboard

#### 1.1 下载Sentinel Dashboard
从[Sentinel官方仓库](https://github.com/alibaba/Sentinel/releases)下载`sentinel-dashboard-1.8.6.jar`到项目根目录。

#### 1.2 启动Dashboard
```powershell
# 在项目根目录执行
java -jar sentinel-dashboard-1.8.6.jar --server.port=8080
```

#### 1.3 验证Dashboard启动
- 访问 http://localhost:8080
- 默认用户名：`sentinel`
- 默认密码：`sentinel`

### 2. 启动Spring Boot应用

#### 2.1 编译项目
```powershell
# 在项目根目录执行
mvn clean compile
```

#### 2.2 启动应用
```powershell
# 在项目根目录执行
mvn spring-boot:run
```

#### 2.3 验证应用启动
- 应用将在端口8081启动
- 访问 http://localhost:8081 查看前端页面
- 访问 http://localhost:8081/sentinel/basic 测试API

### 3. 配置说明

#### 3.1 application.yml配置
```yaml
server:
  port: 8081

spring:
  application:
    name: sentinel-learning-project

# Sentinel配置
spring:
  cloud:
    sentinel:
      transport:
        dashboard: localhost:8080  # Dashboard地址
        port: 8719                 # 应用与Dashboard通信端口
      datasource:
        # 规则持久化配置（可选）
        flow:
          nacos:
            server-addr: localhost:8848
            dataId: ${spring.application.name}-flow-rules
            groupId: SENTINEL_GROUP
            rule-type: flow
```

#### 3.2 关键配置说明
- `transport.dashboard`: Sentinel Dashboard地址
- `transport.port`: 应用与Dashboard通信端口
- `datasource`: 规则持久化配置（使用Nacos时）

## 功能测试

### 1. 基础限流测试
```bash
curl http://localhost:8081/sentinel/basic
```

### 2. 带参数限流测试
```bash
curl "http://localhost:8081/sentinel/param?name=test"
```

### 3. 热点参数测试
```bash
curl "http://localhost:8081/sentinel/hot?userId=123&itemId=456"
```

### 4. 慢调用测试
```bash
curl http://localhost:8081/sentinel/slow
```

### 5. 异常比例测试
```bash
curl http://localhost:8081/sentinel/exception
```

### 6. 系统自适应测试
```bash
curl http://localhost:8081/sentinel/system
```

## Sentinel Dashboard使用

### 1. 查看应用状态
1. 登录Dashboard (http://localhost:8080)
2. 点击左侧菜单"机器列表"
3. 查看应用连接状态

### 2. 配置限流规则
1. 点击"簇点链路"
2. 选择要配置的资源
3. 点击"流控"按钮
4. 配置QPS阈值和策略

### 3. 监控实时数据
- 实时监控：查看QPS、RT、异常数等指标
- 簇点链路：查看所有受保护的资源
- 机器列表：查看应用实例状态

## 常见问题

### 1. 应用无法连接到Dashboard
**问题**: 应用启动后Dashboard中看不到应用
**解决方案**:
- 检查`application.yml`中的`transport.dashboard`配置
- 确认Dashboard已启动且端口8080可访问
- 检查防火墙设置

### 2. 限流规则不生效
**问题**: 配置了限流规则但请求未被限流
**解决方案**:
- 确认资源名称与`@SentinelResource`中的value一致
- 检查规则配置是否正确
- 确认应用已重新加载配置

### 3. 端口冲突
**问题**: 端口8080或8081被占用
**解决方案**:
- 修改`application.yml`中的端口配置
- 或使用`--server.port`参数指定端口

## 扩展功能

### 1. 规则持久化
使用Nacos作为配置中心持久化规则：
```yaml
spring:
  cloud:
    sentinel:
      datasource:
        flow:
          nacos:
            server-addr: localhost:8848
            dataId: ${spring.application.name}-flow-rules
            groupId: SENTINEL_GROUP
            rule-type: flow
```

### 2. 自定义降级处理
```java
@SentinelResource(
    value = "custom-resource",
    blockHandler = "customBlockHandler",
    fallback = "customFallback"
)
public String customMethod() {
    // 业务逻辑
}

public String customBlockHandler(BlockException ex) {
    // 限流降级处理
}

public String customFallback(Throwable ex) {
    // 异常降级处理
}
```

## 总结

本项目成功演示了Sentinel的核心功能：
- ✅ 应用与Dashboard连接正常
- ✅ 各种限流策略可配置
- ✅ 实时监控数据可查看
- ✅ 降级处理机制完善

通过本项目的学习，可以深入理解Sentinel的工作原理和最佳实践。
