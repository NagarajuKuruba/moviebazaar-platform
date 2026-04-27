package com.moviebazaar.payment.service.client;

import lombok.Data;

import java.util.List;

@Data
public class ReleaseSeatsRequest {
    private Long showId;
    private List<Integer> seatNumbers;
    private String userId;
}
