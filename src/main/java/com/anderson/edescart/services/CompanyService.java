package com.anderson.edescart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	@Transactional(readOnly = true)
	public Page<CompanyDTO> findAll(Pageable pageable) {
		Page<Company> result = repository.findAll(pageable);
		return result.map(x -> new CompanyDTO(x));
	}

}
