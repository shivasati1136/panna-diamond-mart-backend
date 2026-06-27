package com.panna.controller;
import com.panna.dto.ProfileUpdateRequest;
import com.panna.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.panna.entity.User;
import com.panna.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/profile")
    public String profile() {
        return "JWT Protected API Working Successfully";
    }

    @GetMapping("/me")
    public User currentUser(Authentication authentication) {

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    @PutMapping("/profile")
    public User updateProfile(
            Authentication authentication,
            @RequestBody ProfileUpdateRequest request
    ) {

        return userService.updateProfile(
                authentication.getName(),
                request
        );
    }
}