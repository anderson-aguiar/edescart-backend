package com.anderson.edescart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anderson.edescart.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
