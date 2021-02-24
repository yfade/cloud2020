package com.mld.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    @LoadBalanced   //如果要用ribbon做负载均衡需要加这个注解
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
