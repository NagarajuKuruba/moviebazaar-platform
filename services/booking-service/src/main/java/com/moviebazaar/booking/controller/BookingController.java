package com.moviebazaar.booking.controller;

import com.moviebazaar.booking.dto.ConfirmBookingRequest;
import com.moviebazaar.booking.dto.LockSeatsRequest;
import com.moviebazaar.booking.service.BookingService;
import com.moviebazaar.common.pagination.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping("/lock")
    public ApiResponse<?> lockSeats(@RequestBody LockSeatsRequest request) {
        return ApiResponse.success("Locked", service.lockSeats(request));
    }

    @PostMapping("/confirm")
    public ApiResponse<?> confirm(@RequestBody ConfirmBookingRequest request) {
        return ApiResponse.success("Booked", service.confirmBooking(request));
    }
}