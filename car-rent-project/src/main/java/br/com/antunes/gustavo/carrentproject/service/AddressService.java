package br.com.antunes.gustavo.carrentproject.service;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.Address;
import br.com.antunes.gustavo.carrentproject.model.dto.AddressDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.CityDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.CountryDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.StateDTO;

@Service
public class AddressService {

	public AddressDTO convertToDTO(Address address) {
		CountryDTO countryDTO = new CountryDTO(address.getCity().getState().getCountry());
		StateDTO stateDTO = new StateDTO(address.getCity().getState());
		stateDTO.setCountryDTO(countryDTO);
		CityDTO cityDTO = new CityDTO(address.getCity());
		cityDTO.setStateDTO(stateDTO);
		
		AddressDTO addressDTO = new AddressDTO(address);
		addressDTO.setCityDTO(cityDTO);
		
		return addressDTO;
	}
}
