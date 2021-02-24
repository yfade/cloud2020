package com.mld.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyLoadBalanceRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();    //ribbon负载均衡规则替换为随机模式（默认为轮询方式）
    }
}
