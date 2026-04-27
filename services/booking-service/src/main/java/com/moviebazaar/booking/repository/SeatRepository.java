package com.moviebazaar.booking.repository;

import com.moviebazaar.booking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    Optional<Seat> findByShowIdAndSeatNumber(Long showId, String seatNumber);

    List<Seat> findByIdIn(List<Long> ids);
}