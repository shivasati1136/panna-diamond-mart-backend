package com.panna.repository;

import com.panna.entity.Address;
import com.panna.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository
        extends JpaRepository<Address, Long> {

    List<Address> findByUser(User user);
}