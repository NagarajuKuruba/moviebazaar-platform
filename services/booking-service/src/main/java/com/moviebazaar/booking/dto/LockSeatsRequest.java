package com.moviebazaar.booking.dto;

import lombok.Data;

import java.util.List;

@Data
public class LockSeatsRequest {
    private Long showId;
    private List<String> seatNumbers;
    private String userId;
}
