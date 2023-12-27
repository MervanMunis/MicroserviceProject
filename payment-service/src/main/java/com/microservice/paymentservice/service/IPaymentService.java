package com.microservice.paymentservice.service;

import com.microservice.paymentservice.dto.PaymentDto;
import com.microservice.paymentservice.dto.PaymentResponse;

public interface IPaymentService {
    Long doPayment(PaymentDto paymentDto);

    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
}
