package com.moviebazaar.theatre.dto;

import lombok.Data;

@Data
public class ScreenRequest {
    private String name;
    private Integer totalSeats;
    private Long theatreId;
}