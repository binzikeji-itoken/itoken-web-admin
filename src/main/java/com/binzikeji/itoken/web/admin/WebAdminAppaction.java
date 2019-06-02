package com.binzikeji.itoken.web.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @Author Bin
 * @Date 2019/4/12 11:02
 **/
@SpringBootApplication(scanBasePackages = "com.binzikeji.itoken")
@EnableDiscoveryClient
@EnableFeignClients
public class WebAdminAppaction {

    public static void main(String[] args) {
        SpringApplication.run(WebAdminAppaction.class, args);
    }
}
