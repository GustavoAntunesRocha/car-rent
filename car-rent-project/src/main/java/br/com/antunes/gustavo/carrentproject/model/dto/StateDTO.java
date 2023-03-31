package br.com.antunes.gustavo.carrentproject.model.dto;

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
}
