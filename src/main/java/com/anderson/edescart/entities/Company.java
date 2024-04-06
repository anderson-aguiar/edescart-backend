package com.anderson.edescart.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phone;

	@OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
	private Address address;

	@ManyToMany
	@JoinTable(name = "tb_company_material", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "material_id"))
	private Set<Material> materials = new HashSet<>();

	public Company() {
	}

	public Company(Long id, String name, String phone, Address addrees) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = addrees;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddrees() {
		return address;
	}

	public void setAddrees(Address addrees) {
		this.address = addrees;
	}

	public Set<Material> getMaterials() {
		return materials;
	}
	public void removeMaterial(Long materialId) {
	    materials.removeIf(material -> material.getId().equals(materialId));
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
		Company other = (Company) obj;
		return Objects.equals(id, other.id);
	}

}
