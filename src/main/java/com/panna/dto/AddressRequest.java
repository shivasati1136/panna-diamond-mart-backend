package com.panna.dto;

import lombok.Data;

@Data
public class AddressRequest {

    private String fullName;

    private String phone;

    private String pincode;

    private String state;

    private String city;

    private String addressLine1;

    private String addressLine2;

    private String landmark;

    private Boolean defaultAddress;

    private String addressType;
}