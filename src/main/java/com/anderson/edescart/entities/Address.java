package com.anderson.edescart.entities;

import java.util.Objects;

import com.anderson.edescart.dto.AddressDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;

	private String cep;
	private Integer number;
	private String city;
	private String state;

	// @JoinColumn(name = "company_id")
	@MapsId
	@OneToOne
	private Company company;

	public Address() {
	}

	public Address(Long id, String street, String cep, Integer number, String city, String state, Company company) {
		super();
		this.id = id;
		this.street = street;
		this.cep = cep;
		this.number = number;
		this.city = city;
		this.state = state;
		this.company = company;
	}

	public Address(AddressDTO entity) {
		this.id = entity.getId();
		this.street = entity.getStreet();
		this.cep = entity.getCep();
		this.number = entity.getNumber();
		this.city = entity.getCity();
		this.state = entity.getState();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}

}
