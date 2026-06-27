package com.panna.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProfileUpdateRequest {

    private String name;

    private String phone;

    private String gender;

    private LocalDate birthday;

    private LocalDate anniversary;

    private LocalDate spouseBirthday;

    private String gstNumber;

    private Boolean smsSubscription;

    private Boolean whatsappSubscription;

    private Boolean emailSubscription;

    private Boolean callSubscription;
}