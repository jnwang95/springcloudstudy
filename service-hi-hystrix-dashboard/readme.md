### Hystrix Dashboard
* 新建工程，取名为service-hi-hystrix-dashboard
* pom.xml文件里里添加如下：
```$xslt
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-actuator</artifactId>
     </dependency>
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
     </dependency>
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
     </dependency>
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
     </dependency>
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
     </dependency>
```

* 在启动类上添加如下注解：
```$JAVA
    @EnableHystrix
    @EnableHystrixDashboard
    @EnableCircuitBreaker
    @EnableEurekaClient
    @EnableDiscoveryClient
    @SpringBootApplication
    public class ServiceHiHystrixDashboardApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(ServiceHiHystrixDashboardApplication.class, args);
        }
    }
```
* 新建HiController
```java
    @RestController
    public class HiController {
        @Value("${server.port}")
        String port;
    
        @RequestMapping("/hi")
        @HystrixCommand(fallbackMethod = "hiError")
        public String home(@RequestParam(value = "name", defaultValue = "wjn") String name) {
            return "hi " + name + " ,i am from port:" + port;
        }
    
        public String hiError(String name) {
            return "hi,"+name+",sorry,error!";
        }
    }
```
* 配置文件如下
```properties
    server.port=8766
    spring.application.name=service-hi-hystrix
    eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
    management.endpoints.web.exposure.include=*
    management.endpoints.web.cors.allowedOrigins=*
    management.endpoints.web.cors.allowedMethods=*
```
* 首先在浏览器上运行一下自己写的接口，如：http://localhost:8766/hi
* 然后在浏览器输入 http://localhost:8766/actuator/hystrix.stream 出现如下：
```
data: {"type":"HystrixCommand","name":"home","group":"HiController",
"currentTime":1576564049042,"isCircuitBreakerOpen":false,"errorPercentage":0,
"errorCount":0,"requestCount":1,"rollingCountBadRequests":0,
"rollingCountCollapsedRequests":0,"rollingCountEmit":0,
"rollingCountExceptionsThrown":0,"rollingCountFailure":0,
"rollingCountFallbackEmit":0,"rollingCountFallbackFailure":0,
"rollingCountFallbackMissing":0,"rollingCountFallbackRejection":0,
"rollingCountFallbackSuccess":0,"rollingCountResponsesFromCache":0,
"rollingCountSemaphoreRejected":0,"rollingCountShortCircuited":0,
"rollingCountSuccess":0,"rollingCountThreadPoolRejected":0,
"rollingCountTimeout":0,"currentConcurrentExecutionCount":0,
"rollingMaxConcurrentExecutionCount":0,"latencyExecute_mean":0,
"latencyExecute":{"0":0,"25":0,"50":0,"75":0,"90":0,"95":0,"99":0,"99.5":0,
"100":0},"latencyTotal_mean":0,"latencyTotal":{"0":0,"25":0,"50":0,"75":0,
"90":0,"95":0,"99":0,"99.5":0,"100":0},
"propertyValue_circuitBreakerRequestVolumeThreshold":20,
"propertyValue_circuitBreakerSleepWindowInMilliseconds":5000,
"propertyValue_circuitBreakerErrorThresholdPercentage":50,
"propertyValue_circuitBreakerForceOpen":false,
"propertyValue_circuitBreakerForceClosed":false,
"propertyValue_circuitBreakerEnabled":true,
"propertyValue_executionIsolationStrategy":"THREAD",
"propertyValue_executionIsolationThreadTimeoutInMilliseconds":1000,
"propertyValue_executionTimeoutInMilliseconds":1000,
"propertyValue_executionIsolationThreadInterruptOnTimeout":true,
"propertyValue_executionIsolationThreadPoolKeyOverride":null,
"propertyValue_executionIsolationSemaphoreMaxConcurrentRequests":10,
"propertyValue_fallbackIsolationSemaphoreMaxConcurrentRequests":10,
"propertyValue_metricsRollingStatisticalWindowInMilliseconds":10000,
"propertyValue_requestCacheEnabled":true,
"propertyValue_requestLogEnabled":true,
"reportingHosts":1,"threadPool":"HiController"}
```
   `注意：如果不先启动下自己写的方法，则启动http://localhost:8766/actuator/hystrix.stream 是会一直ping`
* 在浏览器输入http://127.0.0.1:8766/hystrix/ 会出现hystrix界面。
切记：不要使用localhost,否则打不开界面。 