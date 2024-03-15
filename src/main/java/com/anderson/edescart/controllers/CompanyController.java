package com.anderson.edescart.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anderson.edescart.dto.CompanyDTO;
import com.anderson.edescart.dto.CompanyMinDTO;
import com.anderson.edescart.services.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

	@Autowired
	private CompanyService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<CompanyDTO> findById(@PathVariable Long id) {
		CompanyDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/material")
	public ResponseEntity<List<CompanyDTO>> findByMaterial(
			@RequestParam(name = "name", defaultValue = "") String name) {
		List<CompanyDTO> list = service.findByMaterialName(name);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/distance")
	public ResponseEntity<List<CompanyMinDTO>> findDistance(@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "postalCode") String postalCode) {
		List<CompanyMinDTO> minDto = new ArrayList<>();
		minDto = service.findDistance(name, postalCode);
		return ResponseEntity.ok(minDto);
	}

	@GetMapping()
	public ResponseEntity<Page<CompanyDTO>> findAll(Pageable pageable) {
		Page<CompanyDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping()
	public ResponseEntity<CompanyDTO> insert(@Valid @RequestBody CompanyDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<CompanyDTO> update(@PathVariable Long id, @Valid @RequestBody CompanyDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
