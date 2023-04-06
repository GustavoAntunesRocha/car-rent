package br.com.antunes.gustavo.carrentproject.model.dto;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(cityDTO, id, street, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDTO other = (AddressDTO) obj;
		return Objects.equals(cityDTO, other.cityDTO) && Objects.equals(id, other.id)
				&& Objects.equals(street, other.street) && Objects.equals(zipCode, other.zipCode);
	}
}
