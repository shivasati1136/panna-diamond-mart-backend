package com.panna.repository;

import com.panna.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository
        extends JpaRepository<Cart, Long> {

    List<Cart> findByUserEmail(String userEmail);
}