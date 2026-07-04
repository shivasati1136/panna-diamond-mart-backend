package com.panna.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // USER EMAIL
    private String userEmail;

    // PRODUCT DETAILS
    private Long productId;

    private String productName;

    private Double price;

    private Integer quantity;

    private String imageUrl;

    // ORDER STATUS
    private String status;

    // ORDER DATE
    private LocalDateTime createdAt;

    private String fullName;

    private String mobile;

    private String addressLine;

    private String city;

    private String state;

    private String pincode;

    @PrePersist
    public void prePersist() {

        createdAt = LocalDateTime.now();

        if (status == null) {
            status = "PLACED";
        }
    }
}