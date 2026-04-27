package com.moviebazaar.booking.service;

import com.moviebazaar.booking.dto.ConfirmBookingRequest;
import com.moviebazaar.booking.dto.LockSeatsRequest;
import com.moviebazaar.booking.entity.Booking;
import com.moviebazaar.booking.entity.Seat;
import com.moviebazaar.booking.entity.SeatLock;
import com.moviebazaar.booking.repository.BookingRepository;
import com.moviebazaar.booking.repository.SeatLockRepository;
import com.moviebazaar.booking.repository.SeatRepository;
import com.moviebazaar.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final SeatRepository seatRepo;
    private final SeatLockRepository lockRepo;
    private final BookingRepository bookingRepo;

    private static final int LOCK_TIMEOUT_MIN = 5;

    @Transactional
    public List<Long> lockSeats(LockSeatsRequest request) {

        List<Long> lockedSeatIds = new ArrayList<>();

        for (String seatNum : request.getSeatNumbers()) {

            Seat seat = seatRepo.findByShowIdAndSeatNumber(
                    request.getShowId(),
                    seatNum
            ).orElseThrow(() -> new BaseException("Seat not found","NOT_FOUND"));


            if (seat.isBooked()) {
                throw new RuntimeException("Seat already booked: " + seatNum);
            }

            // 🔒 Check existing lock
            Optional<SeatLock> existingLock = lockRepo.findBySeatId(seat.getId());

            if (existingLock.isPresent()) {

                // check expiry
                if (existingLock.get().getLockTime()
                        .plusMinutes(LOCK_TIMEOUT_MIN)
                        .isAfter(LocalDateTime.now())) {

                    throw new BaseException("Seat already locked: " + seatNum, "SEAT_LOCKED");
                }

                // expired → remove
                lockRepo.delete(existingLock.get());
            }

            // create new lock
            SeatLock lock = new SeatLock();
            lock.setSeatId(seat.getId());
            lock.setUserId(request.getUserId());
            lock.setLockTime(LocalDateTime.now());

            lockRepo.save(lock);

            lockedSeatIds.add(seat.getId());
        }

        return lockedSeatIds;
    }

    @Transactional
    public Booking confirmBooking(ConfirmBookingRequest request) {

        List<Seat> seats = seatRepo.findByIdIn(request.getSeatIds());

        for (Seat seat : seats) {

            SeatLock lock = lockRepo.findBySeatId(seat.getId())
                    .orElseThrow(() -> new BaseException("Seat not locked", "SEAT_NOT_LOCKED"));

            if (!lock.getUserId().equals(request.getUserId())) {
                throw new BaseException("Unauthorized lock", "SEAT_UNAUTHORIZED_LOCK");
            }

            seat.setBooked(true);
            lockRepo.delete(lock);
        }

        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setSeatIds(request.getSeatIds());
        booking.setStatus("CONFIRMED");

        return bookingRepo.save(booking);
    }
}