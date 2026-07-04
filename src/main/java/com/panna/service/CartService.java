package com.panna.service;

import com.panna.dto.CartRequest;
import com.panna.entity.Cart;
import com.panna.entity.Product;
import com.panna.repository.CartRepository;
import com.panna.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    // =========================
    // ADD TO CART
    // =========================
    public Cart addToCart(
            String userEmail,
            CartRequest request
    ) {

        Product product = productRepository.findById(
                request.getProductId()
        ).orElseThrow(() ->
                new RuntimeException("Product not found")
        );
        Cart existingCart = cartRepository
                .findByUserEmailAndProductId(
                        userEmail,
                        product.getId()
                )
                .orElse(null);

        if (existingCart != null) {

            existingCart.setQuantity(
                    existingCart.getQuantity()
                            + request.getQuantity()
            );

            return cartRepository.save(existingCart);
        }

        Cart cart = new Cart();

        cart.setUserEmail(userEmail);

        cart.setProductId(product.getId());

        cart.setProductName(product.getName());

        cart.setPrice(product.getPrice());

        cart.setImageUrl(product.getImageUrl());

        cart.setQuantity(request.getQuantity());

        return cartRepository.save(cart);
    }

    // =========================
    // GET USER CART
    // =========================
    public List<Cart> getUserCart(String userEmail) {

        return cartRepository.findByUserEmail(userEmail);
    }
    // =========================
    // UPDATE CART QUANTITY
    // =========================
    public Cart updateQuantity(
            Long cartId,
            Integer quantity
    ) {

        if (quantity < 1) {
            throw new RuntimeException("Quantity must be at least 1");
        }

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new RuntimeException("Cart item not found"));

        cart.setQuantity(quantity);

        return cartRepository.save(cart);
    }
    // =========================
    // REMOVE CART ITEM
    // =========================
    public String removeCartItem(Long cartId) {

        cartRepository.deleteById(cartId);

        return "Item removed from cart";
    }

}