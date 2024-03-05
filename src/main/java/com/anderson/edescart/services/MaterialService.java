package com.anderson.edescart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anderson.edescart.dto.MaterialDTO;
import com.anderson.edescart.entities.Material;
import com.anderson.edescart.repositories.MaterialRepository;
import com.anderson.edescart.services.exceptions.DatabaseException;
import com.anderson.edescart.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MaterialService {

	@Autowired
	private MaterialRepository materialRepository;

	@Transactional(readOnly = true)
	public MaterialDTO findById(Long id) {
		Material company = materialRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
		MaterialDTO dto = new MaterialDTO(company);
		return dto;
	}

	@Transactional(readOnly = true)
	public Page<MaterialDTO> findAll(Pageable pageable) {
		Page<Material> result = materialRepository.findAll(pageable);
		return result.map(x -> new MaterialDTO(x));
	}

	@Transactional
	public MaterialDTO insert(MaterialDTO dto) {
		Material entity = new Material();

		entity.setName(dto.getName());

		entity = materialRepository.save(entity);

		return new MaterialDTO(entity);
	}

	@Transactional
	public MaterialDTO update(Long id, MaterialDTO dto) {
		try {
			Material entity = materialRepository.getReferenceById(id);
			entity.setName(dto.getName());
			entity = materialRepository.save(entity);
			return new MaterialDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!materialRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			materialRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}
}
