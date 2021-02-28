package com.mld.cloud.controller;

import com.mld.cloud.entities.Payment;
import com.mld.cloud.feign.PaymentFeignClient;
import com.mld.cloud.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/feign")
public class OrderController {
    @Autowired
    private PaymentFeignClient paymentFeignClient;


    @PostMapping("/consumer/payment")
    public CommonResult<Integer> addPayment(Payment payment) {
        return paymentFeignClient.addPayment(payment);
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id) {
        return paymentFeignClient.getPayment(id);
    }

    @GetMapping("/testTimeout")
    public String testTimeout() {
        return paymentFeignClient.testTimeout();
    }

}
