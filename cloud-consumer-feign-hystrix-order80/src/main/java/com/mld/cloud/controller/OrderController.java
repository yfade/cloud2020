package com.mld.cloud.controller;

import com.mld.cloud.feign.PaymentFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/order")
@DefaultProperties(defaultFallback = "defaultErrorHandler")
public class OrderController {
    @Resource
    private PaymentFeignClient paymentFeignClient;


    @GetMapping("/hystrix/ok/{id}")
//    @HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentFeignClient.paymentInfo_OK(id);
        log.info("*******result:" + result);
        int a = 10 / 0;
        return result;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.timeout.enabled", value = "false"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @GetMapping("/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentFeignClient.paymentInfo_TimeOut(id);
        log.info("*******result:" + result);
        return result;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "支付系统繁忙，请稍后再试 " + id;
    }

    public String defaultErrorHandler() {
        return "默认服务降级方法";
    }

}
