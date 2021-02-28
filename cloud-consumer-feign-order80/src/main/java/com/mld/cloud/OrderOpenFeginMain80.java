package com.mld.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //开启Feign
public class OrderOpenFeginMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderOpenFeginMain80.class, args);
    }
}
