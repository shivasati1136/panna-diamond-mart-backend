package com.panna.service;

import com.panna.dto.AuthResponse;
import com.panna.dto.LoginRequest;
import com.panna.dto.RegisterRequest;
import com.panna.entity.User;
import com.panna.repository.UserRepository;
import com.panna.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // =========================
    // REGISTER
    // =========================
    public User register(RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        // ENCRYPT PASSWORD
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        user.setRole("USER");

        return userRepository.save(user);
    }

    // =========================
    // LOGIN
    // =========================
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // CHECK ENCRYPTED PASSWORD
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid Password");
        }

        String accessToken =
                jwtUtil.generateToken(
                        user.getEmail(),
                        user.getRole()
                );

        String refreshToken =
                jwtUtil.generateRefreshToken(
                        user.getEmail()
                );

        return new AuthResponse(

                accessToken,

                refreshToken,

                user.getEmail(),

                user.getRole(),

                user.getName()
        );
    }
    // =========================
// REFRESH TOKEN
// =========================
    public AuthResponse refreshToken(
            String refreshToken
    ) {

        Claims claims =
                jwtUtil.extractAllClaims(
                        refreshToken
                );

        String email =
                claims.getSubject();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String newAccessToken =
                jwtUtil.generateToken(
                        user.getEmail(),
                        user.getRole()
                );

        return new AuthResponse(

                newAccessToken,

                refreshToken,

                user.getEmail(),

                user.getRole(),

                user.getName()
        );
    }
}