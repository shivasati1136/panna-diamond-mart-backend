package com.panna.service;

import com.panna.dto.ProfileUpdateRequest;
import com.panna.entity.User;
import com.panna.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getCurrentUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public User updateProfile(
            String email,
            ProfileUpdateRequest request
    ) {

        User user = getCurrentUser(email);

        user.setName(request.getName());
        user.setPhone(request.getPhone());

        user.setGender(request.getGender());

        user.setBirthday(request.getBirthday());

        user.setAnniversary(
                request.getAnniversary()
        );

        user.setSpouseBirthday(
                request.getSpouseBirthday()
        );

        user.setGstNumber(
                request.getGstNumber()
        );

        user.setSmsSubscription(
                request.getSmsSubscription()
        );

        user.setWhatsappSubscription(
                request.getWhatsappSubscription()
        );

        user.setEmailSubscription(
                request.getEmailSubscription()
        );

        user.setCallSubscription(
                request.getCallSubscription()
        );

        user.setUpdatedAt(
                LocalDateTime.now()
        );

        return userRepository.save(user);
    }
}