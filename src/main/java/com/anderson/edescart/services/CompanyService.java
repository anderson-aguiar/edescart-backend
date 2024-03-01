package com.anderson.edescart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anderson.edescart.dto.AddressDTO;
import com.anderson.edescart.dto.CompanyDTO;
import com.anderson.edescart.dto.MaterialDTO;
import com.anderson.edescart.entities.Address;
import com.anderson.edescart.entities.Company;
import com.anderson.edescart.entities.Material;
import com.anderson.edescart.repositories.CompanyRepository;
import com.anderson.edescart.repositories.MaterialRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private MaterialRepository materialRepository;

	@Transactional(readOnly = true)
	public CompanyDTO findById(Long id) {
		Company company = companyRepository.findById(id).get();
		CompanyDTO dto = new CompanyDTO(company);
		return dto;
	}

	@Transactional(readOnly = true)
	public Page<CompanyDTO> findAll(Pageable pageable) {
		Page<Company> result = companyRepository.findAll(pageable);
		return result.map(x -> new CompanyDTO(x));
	}

	@Transactional
	public CompanyDTO insert(CompanyDTO dto) {
		Company entity = new Company();

		entity.setName(dto.getName());
		entity.setPhone(dto.getPhone());

		AddressDTO addressDTO = dto.getAddress();
		Address address = new Address();
		address.setStreet(addressDTO.getStreet());
		address.setCep(addressDTO.getCep());
		address.setNumber(addressDTO.getNumber());
		address.setCity(addressDTO.getCity());
		address.setState(addressDTO.getState());
		address.setCompany(entity);
		entity.setAddrees(address);

		for (MaterialDTO matDto : dto.getMaterials()) {
			Material mat = materialRepository.getReferenceById(matDto.getId());
			// Material mat = new Material();
			// mat.setId(matDto.getId());
			entity.getMaterials().add(mat);
		}
		entity = companyRepository.save(entity);

		return new CompanyDTO(entity);
	}

}
