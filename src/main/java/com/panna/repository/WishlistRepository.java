package com.panna.repository;

import com.panna.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import java.util.List;

public interface WishlistRepository
        extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByUserEmailOrderByCreatedAtDesc(String userEmail);
    boolean existsByUserEmailAndProductId(
            String userEmail,
            Long productId
    );
    Optional<Wishlist> findByUserEmailAndProductId(
            String userEmail,
            Long productId
    );
}