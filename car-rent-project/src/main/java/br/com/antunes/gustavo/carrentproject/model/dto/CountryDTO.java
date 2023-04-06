package br.com.antunes.gustavo.carrentproject.model.dto;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(code, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryDTO other = (CountryDTO) obj;
		return Objects.equals(code, other.code) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
