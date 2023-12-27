package com.microservice.external.client;

import com.microservice.exception.CustomException;
import com.microservice.external.dto.PaymentDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "payment", url = "${microservice.payment}")
public interface IPaymentService {

    @PostMapping
    ResponseEntity<Long> doPayment(@RequestBody PaymentDto paymentDto);

    default ResponseEntity<Long> fallback(Exception e) {
        throw new CustomException("Payment Service is not available", "UNAVAİLABLE", 500);
    }
}
