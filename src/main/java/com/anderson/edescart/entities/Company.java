package com.anderson.edescart.entities;

import java.util.HashSet;
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
	private Address addrees; 

	@ManyToMany
	@JoinTable(name = "tb_company_material", joinColumns = @JoinColumn(name = "company_id"), 
	inverseJoinColumns = @JoinColumn(name = "material_id"))
	private Set<Material> materials = new HashSet<>();

	public Company() {
	}
	public Company(Long id, String name, String phone, Address addrees) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.addrees = addrees;
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
		return addrees;
	}
	public void setAddrees(Address addrees) {
		this.addrees = addrees;
	}
	public Set<Material> getMaterials() {
		return materials;
	}
	
}
