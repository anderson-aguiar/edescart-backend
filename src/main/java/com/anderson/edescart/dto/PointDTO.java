package com.anderson.edescart.dto;

public class PointDTO {
	private String type;
	private double[] coordinates;

	public PointDTO() {
	}

	public PointDTO(String type, double[] coordinates) {
		this.type = type;
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}

}
