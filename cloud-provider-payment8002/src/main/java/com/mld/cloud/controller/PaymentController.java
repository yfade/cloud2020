package com.mld.cloud.controller;

import com.mld.cloud.service.PaymentService;
import com.mld.cloud.entities.Payment;
import com.mld.cloud.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment")
    public CommonResult<Integer> addPayment(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        if (i > 0) {
            return new CommonResult<>(1, "success-serverPort:" + serverPort, i);
        } else {
            return new CommonResult<>(0, "fail");
        }
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("爱很简单---");
        if (payment != null) {
            return new CommonResult<>(1, "success-serverPort:" + serverPort, payment);
        } else {
            return new CommonResult<>(0, "not found payment");
        }
    }

}
