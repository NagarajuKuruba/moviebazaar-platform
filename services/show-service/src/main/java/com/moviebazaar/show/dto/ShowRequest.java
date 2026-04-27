package com.moviebazaar.show.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequest {

    private Long movieId;
    private Long theatreId;
    private Long screenId;

    private LocalDate showDate;
    private LocalTime showTime;

    private Double price;
}
