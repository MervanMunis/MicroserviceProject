package com.microservice.paymentservice.controller;

import com.microservice.paymentservice.dto.PaymentDto;
import com.microservice.paymentservice.dto.PaymentResponse;
import com.microservice.paymentservice.service.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private IPaymentService paymentService;

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentDto paymentDto) {
        return new ResponseEntity<>(paymentService.doPayment(paymentDto), HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable("orderId") Long orderId){
        return new ResponseEntity<>(paymentService.getPaymentDetailsByOrderId(orderId), HttpStatus.OK);
    }
}
