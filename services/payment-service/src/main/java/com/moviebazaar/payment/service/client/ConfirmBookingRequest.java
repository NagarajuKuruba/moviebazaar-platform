package com.moviebazaar.payment.service.client;

import lombok.Data;

import java.util.List;

@Data
public class ConfirmBookingRequest {
    private String userId;
    private List<Integer> seatIds;
}
