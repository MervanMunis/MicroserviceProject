package com.microservice.controller;


import com.microservice.dto.OrderDto;
import com.microservice.dto.OrderResponse;
import com.microservice.service.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
@Log4j2
public class OrderController {

    private IOrderService orderService;

    @PreAuthorize("hasAuthority('Customer')")
    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderDto orderDto){
        Long orderId = orderService.placeOrder(orderDto);

        log.info("Order Id: {}", orderId);

        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable Long orderId){

        OrderResponse orderResponse = orderService.getOrderDetails(orderId);

        return new ResponseEntity<>(orderResponse, HttpStatus.OK);

    }

}
