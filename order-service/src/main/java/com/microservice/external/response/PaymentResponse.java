package com.microservice.external.response;

import com.microservice.dto.PaymentMode;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private long paymentId;
    private String paymentStatus;
    private PaymentMode paymentMode;
    private long amount;
    private Instant paymentDate;
    private long orderId;

}
