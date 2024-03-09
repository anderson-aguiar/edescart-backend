package com.anderson.edescart.dto;

import java.util.List;

public class ResourceSetDTO {
	private List<ResourceDTO> resources;

	public ResourceSetDTO() {
	}

	public ResourceSetDTO(List<ResourceDTO> resources) {
		super();
		this.resources = resources;
	}

	public List<ResourceDTO> getResources() {
		return resources;
	}

}
