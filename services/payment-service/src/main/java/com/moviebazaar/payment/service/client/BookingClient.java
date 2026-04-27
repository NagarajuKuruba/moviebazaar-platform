package com.moviebazaar.payment.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "booking-service", url = "http://localhost:8086")
public interface BookingClient {

    @PostMapping("/api/v1/bookings/confirm")
    void confirmBooking(ConfirmBookingRequest request);

    @PostMapping("/api/v1/bookings/release")
    void releaseSeats(ReleaseSeatsRequest request);
}