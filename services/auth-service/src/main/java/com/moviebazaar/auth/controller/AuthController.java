package com.moviebazaar.auth.controller;

import com.moviebazaar.auth.dto.AuthResponse;
import com.moviebazaar.auth.dto.LoginRequest;
import com.moviebazaar.auth.dto.RefreshRequest;
import com.moviebazaar.auth.service.AuthService;
import com.moviebazaar.common.pagination.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {

        return ApiResponse.success(
                "Login successful",
                authService.login(request)
        );
    }
    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(@RequestBody RefreshRequest request) {

        AuthResponse newAccessToken = authService.refreshToken(request.getRefreshToken());

        return ApiResponse.success(
                "Token refreshed",
                newAccessToken
        );
    }

    // 🆕 REGISTER
    @PostMapping("/register")
    public ApiResponse<String> register(@RequestBody LoginRequest request) {

        return ApiResponse.success(
                authService.register(request),
                null
        );
    }

    // 🚪 LOGOUT
    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestBody RefreshRequest request) {

        authService.logout(request.getRefreshToken());

        return ApiResponse.success(
                "Logged out successfully",
                null
        );
    }

}
