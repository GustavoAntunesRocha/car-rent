package br.com.antunes.gustavo.carrentproject.model.dto;

import java.util.Objects;

import br.com.antunes.gustavo.carrentproject.model.State;

public class StateDTO {

	private Long id;

	private String name;
	
    private String abbreviation;
    
    private CountryDTO countryDTO;
    
    public StateDTO() {}
    
    public StateDTO(State state) {
    	this.id = state.getId();
    	this.name = state.getName();
    	this.abbreviation = state.getAbbreviation();
    	this.countryDTO = new CountryDTO(state.getCountry());
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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public CountryDTO getCountryDTO() {
		return countryDTO;
	}

	public void setCountryDTO(CountryDTO countryDTO) {
		this.countryDTO = countryDTO;
	}

	@Override
	public int hashCode() {
		return Objects.hash(abbreviation, countryDTO, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateDTO other = (StateDTO) obj;
		return Objects.equals(abbreviation, other.abbreviation) && Objects.equals(countryDTO, other.countryDTO)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
}
