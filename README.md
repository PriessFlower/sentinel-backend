# Sentinel学习项目

这是一个用于学习阿里巴巴Sentinel流控框架的Java项目。

## 项目简介

Sentinel是阿里巴巴开源的面向分布式服务架构的轻量级流量控制框架，主要以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

## 项目结构

```
sentinel-learning-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── sentinel/
│   │   │               ├── SentinelLearningApplication.java  # 主启动类
│   │   │               ├── config/
│   │   │               │   └── SentinelConfig.java          # Sentinel配置类
│   │   │               ├── controller/
│   │   │               │   ├── SentinelController.java      # Sentinel示例控制器
│   │   │               │   └── ServiceController.java       # 服务层示例控制器
│   │   │               └── service/
│   │   │                   └── SentinelService.java         # Sentinel示例服务类
│   │   └── resources/
│   │       ├── application.yml                              # Spring Boot配置
│   │       └── sentinel.properties                          # Sentinel配置
│   └── test/
│       └── java/                                            # 测试代码目录
├── pom.xml                                                  # Maven配置文件
└── README.md                                                # 项目说明文档
```

## 功能特性

### 1. 基础限流示例
- **接口**: `GET /sentinel/basic`
- **功能**: 演示基础的流量控制
- **说明**: 使用`@SentinelResource`注解进行资源保护

### 2. 带参数限流示例
- **接口**: `GET /sentinel/param?name=xxx`
- **功能**: 演示带参数的流量控制
- **说明**: 支持参数化限流

### 3. 慢调用示例
- **接口**: `GET /sentinel/slow`
- **功能**: 演示慢调用检测和限流
- **说明**: 模拟慢调用场景

### 4. 异常比例示例
- **接口**: `GET /sentinel/exception`
- **功能**: 演示异常比例限流
- **说明**: 当异常比例超过阈值时触发限流

### 5. 热点参数限流示例
- **接口**: `GET /sentinel/hot?userId=xxx&itemId=xxx`
- **功能**: 演示热点参数限流
- **说明**: 对热点参数进行特殊限流处理

### 6. 系统自适应限流示例
- **接口**: `GET /sentinel/system`
- **功能**: 演示系统自适应限流
- **说明**: 基于系统负载进行限流

### 7. 服务层示例
- **接口**: `GET /service/database?tableName=xxx`
- **接口**: `GET /service/api?apiName=xxx`
- **接口**: `GET /service/file?fileName=xxx`
- **接口**: `GET /service/cache?key=xxx`
- **功能**: 演示在Service层使用Sentinel
- **说明**: 展示在业务逻辑层进行流量控制

## 技术栈

- **Java 8+**
- **Spring Boot 2.7.18**
- **Alibaba Sentinel 1.8.6**
- **Maven 3.6+**
- **Lombok**

## 快速开始

### 1. 环境要求
- JDK 8或更高版本
- Maven 3.6或更高版本

### 2. 克隆项目
```bash
git clone <项目地址>
cd sentinel-learning-project
```

### 3. 编译项目
```bash
mvn clean compile
```

### 4. 运行项目
```bash
mvn spring-boot:run
```

### 5. 访问应用
- 应用地址: http://localhost:8080
- 健康检查: http://localhost:8080/actuator/health
- Sentinel端点: http://localhost:8080/actuator/sentinel

## 测试示例

### 1. 基础限流测试
```bash
# 快速连续访问，观察限流效果
curl http://localhost:8080/sentinel/basic
```

### 2. 带参数限流测试
```bash
curl "http://localhost:8080/sentinel/param?name=张三"
```

### 3. 慢调用测试
```bash
curl http://localhost:8080/sentinel/slow
```

### 4. 异常比例测试
```bash
# 多次访问，观察异常比例限流
curl http://localhost:8080/sentinel/exception
```

### 5. 热点参数测试
```bash
curl "http://localhost:8080/sentinel/hot?userId=123&itemId=456"
```

### 6. 系统自适应测试
```bash
curl http://localhost:8080/sentinel/system
```

### 7. 服务层测试
```bash
curl "http://localhost:8080/service/database?tableName=users"
curl "http://localhost:8080/service/api?apiName=user-service"
curl "http://localhost:8080/service/file?fileName=test.txt"
curl "http://localhost:8080/service/cache?key=user:123"
```

## 配置说明

### application.yml
- 应用端口配置
- Sentinel控制台地址配置
- 日志级别配置
- Actuator端点配置

### sentinel.properties
- Sentinel核心配置
- 系统规则配置
- 日志配置
- 心跳配置

## 学习要点

### 1. 核心概念
- **资源**: 需要保护的代码块
- **规则**: 流量控制的规则配置
- **指标**: 实时统计的流量数据
- **降级**: 限流后的处理策略

### 2. 流量控制模式
- **直接限流**: 直接对资源进行限流
- **关联限流**: 基于关联资源的限流
- **链路限流**: 基于调用链路的限流

### 3. 流量控制策略
- **QPS限流**: 基于每秒请求数的限流
- **并发线程数限流**: 基于并发线程数的限流
- **系统自适应限流**: 基于系统负载的限流

### 4. 熔断降级
- **慢调用比例**: 基于慢调用比例的熔断
- **异常比例**: 基于异常比例的熔断
- **异常数**: 基于异常数的熔断

## 扩展学习

### 1. 规则持久化
- 使用Nacos作为配置中心
- 使用Apollo作为配置中心
- 使用Redis作为数据源

### 2. 集群流控
- 集群限流模式
- 集群熔断模式
- 集群系统保护

### 3. 热点参数限流
- 参数索引配置
- 参数例外项配置
- 参数统计窗口配置

### 4. 系统保护
- CPU使用率保护
- 平均响应时间保护
- 并发线程数保护
- 系统负载保护

## 常见问题

### 1. 限流不生效
- 检查`@SentinelResource`注解配置
- 检查规则配置是否正确
- 检查资源名称是否匹配

### 2. 降级方法不执行
- 检查降级方法签名是否正确
- 检查方法访问修饰符是否为public
- 检查方法参数是否匹配

### 3. 控制台连接失败
- 检查控制台地址配置
- 检查网络连接
- 检查端口是否被占用

## 参考资料

- [Sentinel官方文档](https://sentinelguard.io/zh-cn/)
- [Sentinel GitHub仓库](https://github.com/alibaba/Sentinel)
- [Spring Boot官方文档](https://spring.io/projects/spring-boot)

## 许可证

本项目仅用于学习目的，请遵循相关开源协议。

