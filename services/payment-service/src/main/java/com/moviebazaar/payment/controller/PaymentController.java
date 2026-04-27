package com.moviebazaar.payment.controller;

import com.moviebazaar.common.pagination.ApiResponse;
import com.moviebazaar.payment.dto.PaymentRequest;
import com.moviebazaar.payment.service.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl service;

    @PostMapping("/initiate")
    public ApiResponse<?> initiate(@RequestBody PaymentRequest request) {
        return ApiResponse.success("Initiated", service.initiate(request));
    }

    @PostMapping("/success/{id}")
    public void success(@PathVariable Long id) {
        service.handleSuccess(id);
    }

    @PostMapping("/failure/{id}")
    public void failure(@PathVariable Long id) {
        service.handleFailure(id);
    }

}
