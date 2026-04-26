package com.moviebazaar.auth.service;

import com.moviebazaar.auth.dto.AuthResponse;
import com.moviebazaar.auth.dto.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);

    String register(LoginRequest request);

    AuthResponse refreshToken(String refreshToken);

    void logout(String refreshToken);
}
