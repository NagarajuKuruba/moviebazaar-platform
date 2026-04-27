package com.moviebazaar.booking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;
    private Long showId;

    @ElementCollection
    private List<Long> seatIds;

    private String status; // CONFIRMED / FAILED
}