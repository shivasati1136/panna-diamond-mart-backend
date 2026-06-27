package com.panna.controller;
import java.util.List;
import com.panna.dto.ReviewRequest;
import com.panna.entity.Review;
import com.panna.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Review addReview(
            Authentication authentication,
            @RequestBody ReviewRequest request
    ) {

        String email = authentication.getName();

        return reviewService.addReview(email, request);
    }
    // =========================
// GET PRODUCT REVIEWS
// =========================
    @GetMapping("/product/{productId}")
    public List<Review> getReviews(
            @PathVariable Long productId
    ) {
        return reviewService.getReviewsByProduct(productId);
    }

    // =========================
    // GET AVERAGE RATING
    // =========================
    @GetMapping("/average/{productId}")
    public Double getAverageRating(
            @PathVariable Long productId
    ) {
        return reviewService.getAverageRating(productId);
    }
}