package com.anderson.edescart.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anderson.edescart.dto.MaterialDTO;
import com.anderson.edescart.services.MaterialService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/materials")
public class MaterialController {

	@Autowired
	private MaterialService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<MaterialDTO> findById(@PathVariable Long id) {
		MaterialDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping()
	public ResponseEntity<List<MaterialDTO>> findAll() {
		List<MaterialDTO> dto = service.findAll();
		return ResponseEntity.ok(dto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping()
	public ResponseEntity<MaterialDTO> insert(@Valid @RequestBody MaterialDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<MaterialDTO> update(@PathVariable Long id, @Valid @RequestBody MaterialDTO dto) {
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
