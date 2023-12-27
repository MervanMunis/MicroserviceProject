package com.microservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private long productId;
    private String name;
    private long price;
    private long quantity;
}
