package com.microservice.paymentservice.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private long paymentId;
    private String status;
    private String paymentMode;
    private long amount;
    private Instant paymentDate;
    private long orderId;

}
