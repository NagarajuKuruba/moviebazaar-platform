package com.moviebazaar.payment.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentRequest {
    private String userId;
    private List<Long> seatIds;
    private Double amount;
}