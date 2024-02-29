package com.anderson.edescart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anderson.edescart.dto.CompanyDTO;
import com.anderson.edescart.services.CompanyService;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
	
	@Autowired
	private CompanyService service;
	
	@GetMapping(value = "/{id}" )
	public CompanyDTO findById(@PathVariable Long id) {
		CompanyDTO dto = service.findById(id);
		return dto;
	}
	@GetMapping()
	public Page<CompanyDTO> findAll(Pageable pageable) {
		Page<CompanyDTO> dto = service.findAll(pageable);
		return dto;
	}
}
