package br.com.antunes.gustavo.carrentproject.model.dto;

import br.com.antunes.gustavo.carrentproject.model.Country;

public class CountryDTO {

	private Long id;

	private String name;

	private String code;

	public CountryDTO() {
	}

	public CountryDTO(Country country) {
		this.id = country.getId();
		this.name = country.getName();
		this.code = country.getCode();
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
