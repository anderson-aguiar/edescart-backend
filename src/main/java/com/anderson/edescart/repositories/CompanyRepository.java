package com.anderson.edescart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anderson.edescart.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	
	List<Company> findByMaterialsNameIgnoreCaseContaining(String materialName);
}
