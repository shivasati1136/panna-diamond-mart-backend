package com.panna.service;

import com.panna.entity.Product;
import com.panna.entity.Wishlist;
import com.panna.repository.ProductRepository;
import com.panna.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    private final ProductRepository productRepository;

    // =========================
    // ADD TO WISHLIST
    // =========================
    public Wishlist addToWishlist(
            String userEmail,
            Long productId
    ) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        Wishlist wishlist = new Wishlist();

        wishlist.setUserEmail(userEmail);

        wishlist.setProductId(product.getId());

        wishlist.setProductName(product.getName());

        wishlist.setPrice(product.getPrice());

        wishlist.setImageUrl(product.getImageUrl());

        return wishlistRepository.save(wishlist);
    }

    // =========================
    // GET USER WISHLIST
    // =========================
    public List<Wishlist> getWishlist(
            String userEmail
    ) {

        return wishlistRepository.findByUserEmail(userEmail);
    }

    // =========================
    // REMOVE FROM WISHLIST
    // =========================
    public String removeWishlistItem(Long id) {

        wishlistRepository.deleteById(id);

        return "Item removed from wishlist";
    }
}