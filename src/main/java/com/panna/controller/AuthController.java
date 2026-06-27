package com.panna.controller;

import com.panna.dto.AuthResponse;
import com.panna.dto.LoginRequest;
import com.panna.dto.RegisterRequest;
import com.panna.entity.User;
import com.panna.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.panna.dto.ApiResponse;
import com.panna.dto.RefreshRequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<User> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        User user = authService.register(request);

        return new ApiResponse<>(
                true,
                "User registered successfully",
                user
        );
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        AuthResponse response = authService.login(request);

        return new ApiResponse<>(
                true,
                "Login successful",
                response
        );
    }
    // REFRESH TOKEN API
    @PostMapping("/refresh")
    public AuthResponse refreshToken(
            @RequestBody RefreshRequest request
    ) {

        return authService.refreshToken(
                request.getRefreshToken()
        );
    }

    @GetMapping("/profile")
    public String profile() {
        return "User Profile API Working";
    }
}