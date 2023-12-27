package com.microservice.external.dto;

import com.microservice.dto.PaymentMode;
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
