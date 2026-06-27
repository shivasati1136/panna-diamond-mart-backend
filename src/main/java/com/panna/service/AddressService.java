package com.panna.service;

import com.panna.dto.AddressRequest;
import com.panna.entity.Address;
import com.panna.entity.User;
import com.panna.repository.AddressRepository;
import com.panna.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    private User getUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    // ADD ADDRESS
    public Address addAddress(
            String email,
            AddressRequest request
    ) {

        User user = getUser(email);

        Address address = new Address();

        address.setFullName(request.getFullName());
        address.setPhone(request.getPhone());
        address.setPincode(request.getPincode());
        address.setState(request.getState());
        address.setCity(request.getCity());
        address.setAddressLine1(request.getAddressLine1());
        address.setAddressLine2(request.getAddressLine2());
        address.setLandmark(request.getLandmark());
        address.setDefaultAddress(
                request.getDefaultAddress()
        );

        address.setUser(user);

        return addressRepository.save(address);
    }

    // GET ALL ADDRESSES
    public List<Address> getAddresses(
            String email
    ) {

        User user = getUser(email);

        return addressRepository.findByUser(user);
    }

    // DELETE ADDRESS
    public void deleteAddress(
            String email,
            Long addressId
    ) {

        User user = getUser(email);

        Address address = addressRepository
                .findById(addressId)
                .orElseThrow(() ->
                        new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        addressRepository.delete(address);
    }

    // SET DEFAULT ADDRESS
    public Address setDefaultAddress(
            String email,
            Long addressId
    ) {

        User user = getUser(email);

        List<Address> addresses =
                addressRepository.findByUser(user);

        for (Address address : addresses) {

            address.setDefaultAddress(false);

            addressRepository.save(address);
        }

        Address selectedAddress =
                addressRepository.findById(addressId)
                        .orElseThrow(() ->
                                new RuntimeException("Address not found"));

        selectedAddress.setDefaultAddress(true);

        return addressRepository.save(selectedAddress);
    }

    public Address updateAddress(
            String email,
            Long addressId,
            AddressRequest request
    ) {

        User user = getUser(email);

        Address address = addressRepository
                .findById(addressId)
                .orElseThrow(() ->
                        new RuntimeException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        address.setFullName(request.getFullName());
        address.setPhone(request.getPhone());
        address.setPincode(request.getPincode());
        address.setState(request.getState());
        address.setCity(request.getCity());
        address.setAddressLine1(request.getAddressLine1());
        address.setAddressLine2(request.getAddressLine2());
        address.setLandmark(request.getLandmark());
        address.setAddressType(request.getAddressType());
        address.setUser(user);
        address.setDefaultAddress(request.getDefaultAddress());
        return addressRepository.save(address);
    }
}