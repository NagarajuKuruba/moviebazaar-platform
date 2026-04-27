package com.moviebazaar.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;
    private Long bookingId;

    private Double amount;

    private List<Integer> seatIds;

    private String status; // INITIATED, SUCCESS, FAILED

    private String transactionId;
}