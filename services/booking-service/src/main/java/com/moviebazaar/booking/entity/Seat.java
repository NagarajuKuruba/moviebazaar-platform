package com.moviebazaar.booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "seats")
@Data
public class Seat {

    @Id
    @GeneratedValue
    private Long id;

    private Long showId;
    private String seatNumber; // A1, A2

    private boolean booked;
}