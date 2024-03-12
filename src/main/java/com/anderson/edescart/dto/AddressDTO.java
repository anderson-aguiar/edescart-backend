package com.anderson.edescart.dto;

import com.anderson.edescart.entities.Address;

import jakarta.validation.constraints.NotBlank;

public class AddressDTO {

	private Long id;
	private String street;
	@NotBlank(message="Campo requerido.")
	private String cep;
	private Integer number;
	private String city;
	private String state;

	public AddressDTO() {
	}

	public AddressDTO(Long id, String street, String cep, Integer number, String city, String state) {
		this.id = id;
		this.street = street;
		this.cep = cep;
		this.number = number;
		this.city = city;
		this.state = state;
	}

	public AddressDTO(Address entity) {
		id = entity.getId();
		street = entity.getStreet();
		cep = entity.getCep();
		number = entity.getNumber();
		city = entity.getCity();
		state = entity.getState();
	}

	public Long getId() {
		return id;
	}

	public String getStreet() {
		return street;
	}

	public String getCep() {
		return cep;
	}

	public Integer getNumber() {
		return number;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}
	
}
