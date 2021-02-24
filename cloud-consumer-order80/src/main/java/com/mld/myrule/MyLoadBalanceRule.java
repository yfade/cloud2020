package com.mld.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * ribbon 默认的负载均衡策略是轮询，原理是 rest接口第几次请求次数%服务集群总数=实际调用的服务器位置下标，
 * 每次重启服务或达到Integer.MAX时rest接口计数从1开始重新计数。
 */
@Component
public class MyLoadBalanceRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();    //ribbon负载均衡规则替换为随机模式（默认为轮询方式）
    }
}
