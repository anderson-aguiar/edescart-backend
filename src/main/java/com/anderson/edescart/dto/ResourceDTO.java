package com.anderson.edescart.dto;

public class ResourceDTO {
	private String name;
	private PointDTO point;

	public ResourceDTO() {
	}

	public ResourceDTO(String name, PointDTO point) {
		this.name = name;
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PointDTO getPoint() {
		return point;
	}

	public void setPoint(PointDTO point) {
		this.point = point;
	}

}
