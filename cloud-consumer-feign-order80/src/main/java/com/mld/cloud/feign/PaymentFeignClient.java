package com.mld.cloud.feign;

import com.mld.cloud.entities.Payment;
import com.mld.cloud.util.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignClient {

    @PostMapping("/payment")
    CommonResult<Integer> addPayment(@RequestBody Payment payment);

    @GetMapping("/payment/{id}")
    CommonResult<Payment> getPayment(@PathVariable(value = "id") Long id);

    @GetMapping("/testTimeout")
    String testTimeout();
}
