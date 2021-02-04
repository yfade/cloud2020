package com.mld.cloud.controller;

import com.mld.cloud.entities.Payment;
import com.mld.cloud.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final String PAYMENT_SERVER_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment")
    public CommonResult addPayment(Payment payment) {
        return restTemplate.postForObject(PAYMENT_SERVER_URL + "/payment", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Integer id) {
        return restTemplate.getForObject(PAYMENT_SERVER_URL + "/payment/" + id, CommonResult.class);
    }

}
