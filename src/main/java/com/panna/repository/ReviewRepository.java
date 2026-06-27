package com.panna.repository;

import com.panna.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProductId(Long productId);

    Optional<Review> findByProductIdAndUserEmail(Long productId, String userEmail);
}