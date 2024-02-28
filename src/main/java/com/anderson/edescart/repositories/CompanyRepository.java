package com.anderson.edescart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anderson.edescart.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
