package com.anderson.edescart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anderson.edescart.dto.CompanyDTO;
import com.anderson.edescart.entities.Company;
import com.anderson.edescart.repositories.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;

	@Transactional(readOnly = true)
	public CompanyDTO findById(Long id) {
		Company company = repository.findById(id).get();
		CompanyDTO dto = new CompanyDTO(company);
		return dto;
	}

}
