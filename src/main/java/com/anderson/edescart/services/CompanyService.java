package com.anderson.edescart.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anderson.edescart.controllers.BingMapsController;
import com.anderson.edescart.dto.AddressDTO;
import com.anderson.edescart.dto.CompanyDTO;
import com.anderson.edescart.dto.CompanyMinDTO;
import com.anderson.edescart.dto.MaterialDTO;
import com.anderson.edescart.entities.Address;
import com.anderson.edescart.entities.Company;
import com.anderson.edescart.entities.Material;
import com.anderson.edescart.repositories.CompanyRepository;
import com.anderson.edescart.repositories.MaterialRepository;
import com.anderson.edescart.services.exceptions.DatabaseException;
import com.anderson.edescart.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private BingMapsController bingService;

	@Transactional(readOnly = true)
	public CompanyDTO findById(Long id) {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
		CompanyDTO dto = new CompanyDTO(company);
		return dto;
	}

	@Transactional(readOnly = true)
	public Page<CompanyDTO> findAll(Pageable pageable) {
		Page<Company> result = companyRepository.searchAll(pageable);
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

	@Transactional
	public CompanyDTO update(Long id, CompanyDTO dto) {
		try {

			Company entity = companyRepository.getReferenceById(id);

			entity.setName(dto.getName());
			entity.setPhone(dto.getPhone());

			AddressDTO addressDTO = dto.getAddress();
			Address address = entity.getAddrees();
			address.setStreet(addressDTO.getStreet());
			address.setCep(addressDTO.getCep());
			address.setNumber(addressDTO.getNumber());
			address.setCity(addressDTO.getCity());
			address.setState(addressDTO.getState());

			for (MaterialDTO matDto : dto.getMaterials()) {
				Material mat = materialRepository.getReferenceById(matDto.getId());
				// Material mat = new Material();
				// mat.setId(matDto.getId());
				entity.getMaterials().add(mat);
			}
			entity = companyRepository.save(entity);

			return new CompanyDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!companyRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			companyRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}

	@Transactional
	public List<CompanyDTO> findByMaterialName(String name) {
		List<CompanyDTO> companiesDto = new ArrayList<>();
		List<Company> companies = companyRepository.findByMaterialsNameIgnoreCaseContaining(name);
		if(companies.isEmpty()) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		for(Company entity : companies ) {
			CompanyDTO dto = new CompanyDTO(entity);
			companiesDto.add(dto);
		}
		return companiesDto;
	}
	@Transactional(readOnly = true)
	public List<CompanyMinDTO> findDistance(String name, String postalCode){
		List<CompanyDTO> list = findByMaterialName(name);
		List<CompanyMinDTO> minDto = new ArrayList<>();
		List<Double> coordCepOrigin = new ArrayList<>();
		coordCepOrigin = bingService.geolocation(postalCode);
		for (CompanyDTO x : list) {
			CompanyMinDTO entity = new CompanyMinDTO();
			entity.setId(x.getId());
			entity.setAddress(x.getAddress());
			entity.setName(x.getName());
			entity.setPhone(x.getPhone() == null ? "Não informado" : x.getPhone());
			String distance = calcDistance(coordCepOrigin, x.getAddress().getCep());
			entity.setDistance(Double.parseDouble(distance));
			minDto.add(entity);

		}
		minDto.sort(Comparator.comparing(CompanyMinDTO::getDistance));
		return minDto;
	}
	public String calcDistance(List<Double> coordCepOrigin, String cepDestination) {
		DecimalFormat df = new DecimalFormat("0.00");
		Double distance;
		List<Double> coordCepDestin = new ArrayList<>();
		coordCepDestin = bingService.geolocation(cepDestination);
		// Formula de Haversine
		double lat1 = Math.toRadians(coordCepOrigin.get(0));
		double lat2 = Math.toRadians(coordCepDestin.get(0));
		double dLat = lat2 - lat1;
		double dLon = Math.toRadians(coordCepDestin.get(1) - coordCepOrigin.get(1));

		double a = Math.pow(Math.sin(dLat / 2), 2)
				+ Math.pow(Math.sin(dLon / 2), 2)
				* Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.asin(Math.sqrt(a));
		distance = c * 6371;
		
		return df.format(distance);
	}

}
