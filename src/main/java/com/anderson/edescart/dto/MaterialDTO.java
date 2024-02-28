package com.anderson.edescart.dto;

import com.anderson.edescart.entities.Material;

public class MaterialDTO {

	private Long id;
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
