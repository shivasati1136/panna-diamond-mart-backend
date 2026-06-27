package com.panna.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // USER EMAIL
    private String userEmail;

    // PRODUCT ID
    private Long productId;

    // PRODUCT NAME
    private String productName;

    // PRODUCT PRICE
    private Double price;

    // PRODUCT IMAGE
    private String imageUrl;

    // QUANTITY
    private Integer quantity;
}