package com.mld.cloud.feign;

import org.springframework.stereotype.Service;

@Service
public class PaymentFallback implements PaymentFeignClient {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "this is PaymentFallback paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "this is PaymentFallback paymentInfo_TimeOut";
    }
}
