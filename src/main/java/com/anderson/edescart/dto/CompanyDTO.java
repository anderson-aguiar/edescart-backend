package com.anderson.edescart.dto;

import com.anderson.edescart.entities.Company;

public class CompanyDTO {

	private Long id;
	private String name;
	private String phone;

	public CompanyDTO() {
	}

	public CompanyDTO(Long id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public CompanyDTO(Company entity) {
		id = entity.getId();
		name = entity.getName();
		phone = entity.getPhone();
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

}
