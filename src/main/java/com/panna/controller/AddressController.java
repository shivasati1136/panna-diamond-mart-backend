package com.panna.controller;

import com.panna.dto.AddressRequest;
import com.panna.entity.Address;
import com.panna.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public Address addAddress(
            @RequestBody AddressRequest request,
            Authentication authentication
    ) {

        return addressService.addAddress(
                authentication.getName(),
                request
        );
    }

    @GetMapping
    public List<Address> getAddresses(
            Authentication authentication
    ) {

        return addressService.getAddresses(
                authentication.getName()
        );
    }

    @DeleteMapping("/{id}")
    public String deleteAddress(
            @PathVariable Long id,
            Authentication authentication
    ) {

        addressService.deleteAddress(
                authentication.getName(),
                id
        );

        return "Address Deleted Successfully";
    }

    @PutMapping("/{id}")
    public Address updateAddress(
            @PathVariable Long id,
            @RequestBody AddressRequest request,
            Authentication authentication
    ) {

        return addressService.updateAddress(
                authentication.getName(),
                id,
                request
        );
    }

    @PutMapping("/default/{id}")
    public Address setDefaultAddress(
            @PathVariable Long id,
            Authentication authentication
    ) {

        return addressService.setDefaultAddress(
                authentication.getName(),
                id
        );
    }
}