package com.moviebazaar.payment.service;


import com.moviebazaar.payment.dto.PaymentRequest;
import com.moviebazaar.payment.entity.Payment;
import com.moviebazaar.payment.dto.PaymentResponse;
import com.moviebazaar.payment.repository.PaymentRepository;
import com.moviebazaar.payment.service.client.BookingClient;
import com.moviebazaar.payment.service.client.ConfirmBookingRequest;
import com.moviebazaar.payment.service.client.ReleaseSeatsRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl {

    private final PaymentRepository paymentRepo;
    private final BookingClient bookingClient;

    // 🟢 INITIATE PAYMENT
    @Transactional
    public PaymentResponse initiate(PaymentRequest request) {

        Payment payment = new Payment();
        payment.setUserId(request.getUserId());
        payment.setAmount(request.getAmount());
        payment.setStatus("INITIATED");

        // (optional) store seatIds for later processing
        //payment.setSeatIds(request.getSeatIds());

        payment = paymentRepo.save(payment);

        // Simulated payment URL (replace with Razorpay/Stripe later)
        String paymentUrl = "http://payment-gateway/pay/" + payment.getId();

        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .status(payment.getStatus())
                .paymentUrl(paymentUrl)
                .build();
    }

    // 🟢 PAYMENT SUCCESS

    @Transactional
    public void handleSuccess(Long paymentId) {

        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Idempotency check
        if ("SUCCESS".equals(payment.getStatus())) {
            return;
        }

        payment.setStatus("SUCCESS");
        paymentRepo.save(payment);

        // 🔗 Call booking-service to confirm booking
        ConfirmBookingRequest request = new ConfirmBookingRequest();
        request.setUserId(payment.getUserId());
        request.setSeatIds(payment.getSeatIds());

        bookingClient.confirmBooking(request);
    }

    // 🔴 PAYMENT FAILURE

    @Transactional
    public void handleFailure(Long paymentId) {

        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Idempotency check
        if ("FAILED".equals(payment.getStatus())) {
            return;
        }

        payment.setStatus("FAILED");
        paymentRepo.save(payment);

        // 🔗 Release locked seats

        // Build release request
        ReleaseSeatsRequest request = new ReleaseSeatsRequest();
        request.setUserId(payment.getUserId());
       // request.setShowId(); // Assuming bookingId is same as showId for simplicity
        request.setSeatNumbers(payment.getSeatIds());

        bookingClient.releaseSeats(request);
    }

}