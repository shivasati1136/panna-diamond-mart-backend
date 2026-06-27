package com.panna.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String password;

    private String role;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String gender;

    private java.time.LocalDate birthday;

    private java.time.LocalDate anniversary;

    @Column(name = "spouse_birthday")
    private java.time.LocalDate spouseBirthday;

    @Column(name = "gst_number")
    private String gstNumber;

    @Column(name = "sms_subscription")
    private Boolean smsSubscription = true;

    @Column(name = "whatsapp_subscription")
    private Boolean whatsappSubscription = true;

    @Column(name = "email_subscription")
    private Boolean emailSubscription = true;

    @Column(name = "call_subscription")
    private Boolean callSubscription = false;

    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Address> addresses;
}