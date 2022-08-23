package com.atguigu.gmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName GMallGetwayMainType.java
 * @Description TODO
 * @createTime 2022年08月20日 13:31:00
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GMallGatewayMainType {

    public static void main(String[] args) {
        SpringApplication.run(GMallGatewayMainType.class, args);
    }
}
