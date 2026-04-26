package com.moviebazaar.auth.service.impl;

import com.moviebazaar.auth.communication.UserServiceClient;
import com.moviebazaar.auth.dto.AuthResponse;
import com.moviebazaar.auth.dto.LoginRequest;
import com.moviebazaar.auth.entity.AuthUser;
import com.moviebazaar.auth.entity.RefreshToken;
import com.moviebazaar.auth.repository.AuthRepository;
import com.moviebazaar.auth.repository.RefreshTokenRepository;
import com.moviebazaar.auth.service.AuthService;
import com.moviebazaar.auth.util.JwtUtil;
import com.moviebazaar.common.dto.user.UserResponse;
import com.moviebazaar.common.exception.InvalidCredentialsException;
import com.moviebazaar.common.exception.UserAlreadyExistsException;
import com.moviebazaar.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepo;
    private final RefreshTokenRepository refreshRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    private final UserServiceClient userClient;

    // 🔐 LOGIN
    @Override
    public AuthResponse login(LoginRequest request) {

        AuthUser user = authRepo.findByUsername(request.getUsername())
                .orElseThrow(UserAlreadyExistsException::new);

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );

        String refreshToken = createRefreshToken(user.getUsername());

        return new AuthResponse(accessToken, refreshToken);
    }

    // 🆕 REGISTER
    @Override
    public String register(LoginRequest request) {

        authRepo.findByUsername(request.getUsername())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException();
                });

        UserResponse userInfo = userClient.getByUsername(request.getUsername());
        if(userInfo==null){
            throw new UserNotFoundException();
        }

        AuthUser user = new AuthUser();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");

        authRepo.save(user);

        return "User registered successfully";
    }

    // 🔄 REFRESH TOKEN
    @Override
    public AuthResponse refreshToken(String refreshToken) {

        RefreshToken token = refreshRepo.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        String newAccessToken = jwtUtil.generateToken(
                token.getUsername(),
                "ROLE_USER"
        );

        return new AuthResponse(newAccessToken, refreshToken);
    }

    // 🚪 LOGOUT
    @Override
    public void logout(String refreshToken) {
        refreshRepo.findByToken(refreshToken)
                .ifPresent(refreshRepo::delete);
    }

    // 🔧 PRIVATE HELPER
    private String createRefreshToken(String username) {

        String token = UUID.randomUUID().toString();

        RefreshToken entity = new RefreshToken();
        entity.setToken(token);
        entity.setUsername(username);
        entity.setExpiryDate(LocalDateTime.now().plusDays(7));

        refreshRepo.save(entity);

        return token;
    }
}