package com.mld.cloud.service;

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
}
