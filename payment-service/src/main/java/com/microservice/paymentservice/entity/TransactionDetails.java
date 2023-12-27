package com.microservice.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "TRANSACTION_DETAILS")
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "MODE")
    private String paymentMode;

    @Column(name = "REFERENCE_NUMBER")
    private String referenceNumber;

    @Column(name = "PAYMENT_DATE")
    private Instant paymentDate;

    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;

    @Column(name = "AMOUNT")
    private Long amount;
}
