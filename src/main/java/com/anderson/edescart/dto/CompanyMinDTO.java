package com.anderson.edescart.dto;

public class CompanyMinDTO {

	private Long id;
	private String name;
	private String phone;
	private Double distance;


	private AddressDTO address;
	

	public CompanyMinDTO() {
	}

	public CompanyMinDTO(Long id, String name, String phone, Double distance, AddressDTO address) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.distance = distance;
		this.address = address;
	}
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}


	
	
}
