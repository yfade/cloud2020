package com.mld.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "\tpaymentInfo_OK,id:" + id + "\t哈哈哈";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.timeout.enabled", value = "false"),    //HystrixCommand 是否开启超时时间控制，默认开启，超时时间为1000ms
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),    //超时时间，如果超时就进行服务降级
    })
    public String paymentInfo_TimeOut(Integer id) {
        int num = 3;
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "\tpaymentInfo_TimeOut,id:" + id + "\t呜呜呜，耗时：" + num;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return Thread.currentThread().getName() + " 系统繁忙，请稍后再试。。。";
    }


    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),    //开启断路器(默认true)
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),   //10s内使熔断判断机制开始工作的最小请求次数（默认20）
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),    //熔断多少秒后去尝试请求(半开，默认5s)
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50") //失败率达到多少百分比后熔断(默认50%)
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功，流水号：" + uuid;
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " + id;
    }
}
