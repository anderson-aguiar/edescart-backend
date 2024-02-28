package com.anderson.edescart.dto;

import java.util.ArrayList;
import java.util.List;

import com.anderson.edescart.entities.Company;
import com.anderson.edescart.entities.Material;

public class CompanyDTO {

	private Long id;
	private String name;
	private String phone;

	private AddressDTO address;
	
	private List<MaterialDTO> materials = new ArrayList<>();

	public CompanyDTO() {
	}

	public CompanyDTO(Long id, String name, String phone, AddressDTO address) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public CompanyDTO(Company entity) {
		id = entity.getId();
		name = entity.getName();
		phone = entity.getPhone();
		address = new AddressDTO(entity.getAddrees());
		for(Material mat : entity.getMaterials()) {
			materials.add(new MaterialDTO(mat));
		}
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

	public List<MaterialDTO> getMaterials() {
		return materials;
	}
	
	
}
