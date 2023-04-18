package br.com.antunes.gustavo.carrentproject.service;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.Address;
import br.com.antunes.gustavo.carrentproject.model.City;
import br.com.antunes.gustavo.carrentproject.model.dto.AddressDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.CityDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.CountryDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.StateDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressService {
	
	private final CityRepository cityRepository;
	
	public AddressService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	public AddressDTO convertToDTO(Address address) {
		if (address == null) {
			return null;
		}
		CountryDTO countryDTO = new CountryDTO(address.getCity().getState().getCountry());
		StateDTO stateDTO = new StateDTO(address.getCity().getState());
		stateDTO.setCountryDTO(countryDTO);
		CityDTO cityDTO = new CityDTO(address.getCity());
		cityDTO.setStateDTO(stateDTO);
		
		AddressDTO addressDTO = new AddressDTO(address);
		addressDTO.setCityDTO(cityDTO);
		
		return addressDTO;
	}

	public Address convertToEntity(AddressDTO addressDTO) throws EntityNotFoundException{
		if (addressDTO == null) {
			return null;
		}
		Address address = new Address();
		address.setId(addressDTO.getId());
		address.setStreet(addressDTO.getStreet());
		address.setZipCode(addressDTO.getZipCode());
		if(addressDTO.getCityDTO() == null) {
			throw new EntityNotFoundException("City not found");
		}
		City city = cityRepository.findById(addressDTO.getCityDTO().getId()).orElseThrow(() -> new EntityNotFoundException("City not found"));
		address.setCity(city);
		return address;
	}
}
