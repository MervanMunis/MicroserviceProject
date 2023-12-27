package com.microservice.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private long productId;
    private long totalAmount;
    private long quantity;
    private PaymentMode paymentMode;

}
