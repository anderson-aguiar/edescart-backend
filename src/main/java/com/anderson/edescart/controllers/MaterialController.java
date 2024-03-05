package com.anderson.edescart.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Page<MaterialDTO>> findAll(Pageable pageable) {
		Page<MaterialDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);
	}

	@PostMapping()
	public ResponseEntity<MaterialDTO> insert(@Valid @RequestBody MaterialDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MaterialDTO> update(@PathVariable Long id, @Valid @RequestBody MaterialDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
