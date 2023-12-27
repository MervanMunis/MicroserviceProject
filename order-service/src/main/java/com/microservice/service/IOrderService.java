package com.microservice.service;

import com.microservice.dto.OrderDto;
import com.microservice.dto.OrderResponse;


public interface IOrderService {
    long placeOrder(OrderDto orderDto);
    OrderResponse getOrderDetails(long orderId);
}
