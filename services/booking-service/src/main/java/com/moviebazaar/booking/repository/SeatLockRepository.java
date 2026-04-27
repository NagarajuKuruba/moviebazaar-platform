package com.moviebazaar.booking.repository;

import com.moviebazaar.booking.entity.SeatLock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatLockRepository extends JpaRepository<SeatLock, Long> {

    Optional<SeatLock> findBySeatId(Long seatId);

    void deleteBySeatId(Long seatId);
}