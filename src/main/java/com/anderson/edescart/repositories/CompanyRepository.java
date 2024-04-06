package com.anderson.edescart.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anderson.edescart.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	
	List<Company> findByMaterialsNameIgnoreCaseContaining(String materialName);
	List<Company> findByMaterialsId(Long id);
	
	@Query(value = "SELECT obj FROM Company obj JOIN FETCH obj.materials JOIN FETCH obj.address",
			countQuery = "SELECT COUNT(obj) FROM Company obj JOIN obj.address")
	Page<Company> searchAll(Pageable pageable);
}
