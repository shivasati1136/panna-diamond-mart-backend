package com.panna.controller;

import com.panna.dto.CartRequest;
import com.panna.entity.Cart;
import com.panna.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    // =========================
    // ADD TO CART
    // =========================
    @PostMapping("/add")
    public Cart addToCart(

            Authentication authentication,

            @RequestBody CartRequest request
    ) {

        String email = authentication.getName();

        return cartService.addToCart(email, request);
    }

    // =========================
    // GET USER CART
    // =========================
    @GetMapping
    public List<Cart> getUserCart(
            Authentication authentication
    ) {

        String email = authentication.getName();

        return cartService.getUserCart(email);
    }
    // =========================
    // UPDATE QUANTITY
    // =========================
    @PutMapping("/update/{id}")
    public Cart updateQuantity(

            @PathVariable Long id,

            @RequestParam Integer quantity
    ) {

        return cartService.updateQuantity(
                id,
                quantity
        );
    }
    // =========================
    // REMOVE CART ITEM
    // =========================
    @DeleteMapping("/remove/{id}")
    public String removeCartItem(
            @PathVariable Long id
    ) {

        return cartService.removeCartItem(id);
    }
}