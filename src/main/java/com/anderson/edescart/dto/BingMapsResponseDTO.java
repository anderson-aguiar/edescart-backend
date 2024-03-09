package com.anderson.edescart.dto;

import java.util.List;

public class BingMapsResponseDTO {
	private List<ResourceSetDTO> resourceSets;

	public BingMapsResponseDTO() {
	}

	public BingMapsResponseDTO(List<ResourceSetDTO> resourceSets) {
		this.resourceSets = resourceSets;
	}

	public List<ResourceSetDTO> getResourceSets() {
		return resourceSets;
	}
	
	
}
