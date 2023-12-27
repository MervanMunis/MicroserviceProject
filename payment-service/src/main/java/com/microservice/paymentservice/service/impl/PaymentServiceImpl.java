package com.microservice.paymentservice.service.impl;

import com.microservice.paymentservice.dto.PaymentDto;
import com.microservice.paymentservice.dto.PaymentMode;
import com.microservice.paymentservice.dto.PaymentResponse;
import com.microservice.paymentservice.entity.TransactionDetails;
import com.microservice.paymentservice.repository.ITransactionDetailsRepository;
import com.microservice.paymentservice.service.IPaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@AllArgsConstructor
@Service
@Log4j2
public class PaymentServiceImpl implements IPaymentService {

    private ITransactionDetailsRepository transactionDetailsRepository;

    @Override
    public Long doPayment(PaymentDto paymentDto) {
        log.info("Recording Payment Details: {}", paymentDto);

        TransactionDetails transactionDetails = TransactionDetails
                .builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentDto.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentDto.getOrderId())
                .referenceNumber(paymentDto.getReferenceNumber())
                .amount(paymentDto.getAmount())
                .build();

        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction Completed with Id: {}", transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(Long orderId) {
        log.info("Getting payment details for the Order Id: {}", orderId);

        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(orderId);

        PaymentResponse paymentResponse = PaymentResponse
                .builder()
                .paymentId(transactionDetails.getId())
                .paymentMode(String.valueOf(PaymentMode.valueOf(transactionDetails.getPaymentMode())))
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .build();
        return paymentResponse;
    }
}
