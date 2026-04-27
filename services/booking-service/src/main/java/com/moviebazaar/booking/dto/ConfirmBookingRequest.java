package com.moviebazaar.booking.dto;

import lombok.Data;

import java.util.List;

@Data
public class ConfirmBookingRequest {
    private String userId;
    private List<Long> seatIds;
}