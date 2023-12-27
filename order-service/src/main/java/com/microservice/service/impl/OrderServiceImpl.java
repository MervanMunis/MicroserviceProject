package com.microservice.service.impl;

import com.microservice.dto.*;
import com.microservice.entity.Order;
import com.microservice.exception.CustomException;
import com.microservice.external.client.IPaymentService;
import com.microservice.external.client.IProductService;
import com.microservice.external.dto.PaymentDto;

import com.microservice.external.response.PaymentResponse;
import com.microservice.external.response.ProductResponse;
import com.microservice.repository.IOrderRepository;
import com.microservice.service.IOrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Log4j2
public class OrderServiceImpl implements IOrderService {

    private IOrderRepository orderRepository;

    private IProductService productService;

    private IPaymentService paymentService;

    private RestTemplate restTemplate;


    @Override
    public long placeOrder(OrderDto orderDto) {
        log.info("Placing Order Request: {}", orderDto);

        productService.reduceQuantity(orderDto.getProductId(), orderDto.getQuantity());
        log.info("Creating Order With Status CREATED");

        Order order = Order.builder()
                .amount(orderDto.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderDto.getProductId())
                .orderDate(Instant.now())
                .quantity(orderDto.getQuantity())
                .build();

        Order savedOrder = orderRepository.save(order);

        log.info("Calling Payment Service To Complete The Payment");
        PaymentDto paymentDto = PaymentDto
                .builder()
                .orderId(savedOrder.getId())
                .paymentMode(orderDto.getPaymentMode())
                .amount(orderDto.getTotalAmount())
                .build();

        String orderStatus;
        try {
            paymentService.doPayment(paymentDto);
            log.info("Payment done Successfully. Changing Order Status to PLACED");
            orderStatus = "PLACED";
        }catch (Exception e){
            log.error("Error occurred in payment. Changing order status to PAYMENT_FAILED");
            orderStatus="PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("Order Places Successfully with Order Id: {}", order.getId());
        return savedOrder.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get order details for Order Id: {}", orderId);

        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new CustomException("Order not found for the Order Id {}:" + orderId, "NOT_FOUND",404));

        log.info("Invoking Product Service to fetch the product for Id: {}", order.getProductId());
        ProductResponse productResponse = restTemplate.getForObject(
                "http://PRODUCT-SERVICE/product/" + order.getProductId(), ProductResponse.class);

        log.info("Getting payment information from the payment service");
        PaymentResponse paymentResponse = restTemplate
                .getForObject("http://PAYMENT-SERVICE/payment/order/" + order.getId(), PaymentResponse.class);


        assert productResponse != null;
        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .build();

        assert paymentResponse != null;
        OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentStatus(paymentResponse.getPaymentStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();
    }
}
