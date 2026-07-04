package com.panna.controller;

import com.panna.entity.Wishlist;
import com.panna.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WishlistController {

    private final WishlistService wishlistService;

    // =========================
    // ADD TO WISHLIST
    // =========================
    @PostMapping("/add/{productId}")
    public Wishlist addToWishlist(

            Authentication authentication,

            @PathVariable Long productId
    ) {

        String email = authentication.getName();

        return wishlistService.addToWishlist(
                email,
                productId
        );
    }

    // =========================
    // GET USER WISHLIST
    // =========================
    @GetMapping
    public List<Wishlist> getWishlist(
            Authentication authentication
    ) {

        String email = authentication.getName();

        return wishlistService.getWishlist(email);
    }

    // =========================
    // REMOVE WISHLIST ITEM
    // =========================
    @DeleteMapping("/remove/{id}")
    public String removeWishlistItem(
            @PathVariable Long id
    ) {

        return wishlistService.removeWishlistItem(id);
    }
    // =========================
// CHECK WISHLIST
// =========================
    @GetMapping("/check/{productId}")
    public boolean isWishlisted(
            Authentication authentication,
            @PathVariable Long productId
    ) {

        String email = authentication.getName();

        return wishlistService.isWishlisted(
                email,
                productId
        );
    }
}