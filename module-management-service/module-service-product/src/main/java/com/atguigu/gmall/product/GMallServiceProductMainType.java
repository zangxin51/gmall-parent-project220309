package com.atguigu.gmall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cqs
 * @version 1.0.0
 * @ClassName GMallServiceProductMainType.java
 * @Description TODO
 * @createTime 2022年08月19日 11:12:00
 */
@SpringBootApplication
@ComponentScan(value = "com.atguigu.gmall")
@EnableDiscoveryClient
public class GMallServiceProductMainType {

    public static void main(String[] args) {

        SpringApplication.run(GMallServiceProductMainType.class, args);

    }
}
