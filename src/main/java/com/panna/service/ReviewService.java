package com.panna.service;

import com.panna.dto.ReviewRequest;
import com.panna.entity.Review;
import com.panna.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(String userEmail, ReviewRequest request) {

        if (reviewRepository.findByProductIdAndUserEmail(
                request.getProductId(),
                userEmail
        ).isPresent()) {

            throw new RuntimeException("You have already reviewed this product.");
        }

        Review review = new Review();

        review.setProductId(request.getProductId());
        review.setUserEmail(userEmail);
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        return reviewRepository.save(review);
    }
    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public Double getAverageRating(Long productId) {

        List<Review> reviews = reviewRepository.findByProductId(productId);

        if (reviews.isEmpty()) {
            return 0.0;
        }

        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);
    }
}