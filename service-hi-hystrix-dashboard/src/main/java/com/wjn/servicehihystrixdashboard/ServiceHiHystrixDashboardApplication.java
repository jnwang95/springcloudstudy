package com.wjn.servicehihystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @EnableCircuitBreaker ：启动断路器
 * @EnableHystrix : 开启断路器
 * @EnableHystrixDashboard : 开启HystrixDashboard
 */
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
