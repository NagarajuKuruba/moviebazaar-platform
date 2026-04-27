package com.moviebazaar.show.entiry;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shows")
@Data
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long movieId;
    private Long theatreId;
    private Long screenId;

    private LocalDate showDate;
    private LocalTime showTime;

    private Double price;
}