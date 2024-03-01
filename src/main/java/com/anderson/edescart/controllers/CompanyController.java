package com.anderson.edescart.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anderson.edescart.dto.CompanyDTO;
import com.anderson.edescart.services.CompanyService;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
	
	@Autowired
	private CompanyService service;
	
	@GetMapping(value = "/{id}" )
	public ResponseEntity<CompanyDTO> findById(@PathVariable Long id) {
		CompanyDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	@GetMapping()
	public ResponseEntity<Page<CompanyDTO>> findAll(Pageable pageable) {
		Page<CompanyDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}
	@PostMapping()
	public  ResponseEntity<CompanyDTO> insert(@RequestBody CompanyDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return  ResponseEntity.created(uri).body(dto);
	}
}
