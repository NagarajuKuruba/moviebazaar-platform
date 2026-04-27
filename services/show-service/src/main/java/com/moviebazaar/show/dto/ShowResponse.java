package com.moviebazaar.show.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ShowResponse {

    private Long id;
    private Long movieId;
    private String movieName;

    private Long theatreId;
    private String theatreName;
    private String city;

    private Long screenId;

    private LocalDate showDate;
    private LocalTime showTime;

    private Double price;
}