package com.microservice.paymentservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {

    private long orderId;
    private long amount;
    private String referenceNumber;
    private PaymentMode paymentMode;

}
