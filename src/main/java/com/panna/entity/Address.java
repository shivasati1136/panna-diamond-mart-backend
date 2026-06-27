package com.panna.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String phone;

    private String pincode;

    private String state;

    private String city;

    private String addressLine1;

    private String addressLine2;

    private String landmark;

    private Boolean defaultAddress = false;

    private String addressType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}