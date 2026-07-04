package com.panna.repository;
import java.util.Optional;

import com.panna.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository
        extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserEmailAndProductId(
            String userEmail,
            Long productId
    );
    List<Cart> findByUserEmail(String userEmail);
}