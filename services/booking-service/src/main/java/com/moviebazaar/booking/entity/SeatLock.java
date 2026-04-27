package com.moviebazaar.booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "seat_locks")
@Data
public class SeatLock {

    @Id
    @GeneratedValue
    private Long id;

    private Long seatId;
    private String userId;

    private LocalDateTime lockTime;
}
