package com.mld.cloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "\tpaymentInfo_OK,id:" + id + "\t哈哈哈";
    }

    public String paymentInfo_TimeOut(Integer id) {
        int num = 3;
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "\tpaymentInfo_TimeOut,id:" + id + "\t呜呜呜，耗时：" + num;
    }
}
