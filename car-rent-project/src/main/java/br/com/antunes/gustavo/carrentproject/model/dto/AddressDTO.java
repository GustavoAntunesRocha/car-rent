package br.com.antunes.gustavo.carrentproject.model.dto;

import br.com.antunes.gustavo.carrentproject.model.Address;

public class AddressDTO {

	private Long id;

	private String street;

	private String zipCode;
	
	private CityDTO cityDTO;
	
	public AddressDTO() {}
	
	public AddressDTO(Address address) {
		this.id = address.getId();
		this.street = address.getStreet();
		this.zipCode = address.getZipCode();
		this.cityDTO = new CityDTO(address.getCity());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public CityDTO getCityDTO() {
		return cityDTO;
	}

	public void setCityDTO(CityDTO cityDTO) {
		this.cityDTO = cityDTO;
	}
}
