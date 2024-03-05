package com.anderson.edescart.dto;

import com.anderson.edescart.entities.Material;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MaterialDTO {

	private Long id;
	@NotBlank(message="Campo requerido.")	
	@Size(min=3, max=15, message="Nome precisa ter de 3 a 15 caracteres.")
	private String name;

	public MaterialDTO() {
	}

	public MaterialDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public MaterialDTO(Material entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	

}
