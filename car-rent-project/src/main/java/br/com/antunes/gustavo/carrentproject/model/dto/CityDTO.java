package br.com.antunes.gustavo.carrentproject.model.dto;

import br.com.antunes.gustavo.carrentproject.model.City;

public class CityDTO {
	
	private Long id;

	private String name;
	
	private StateDTO stateDTO;
	
	public CityDTO() {}
	
	public CityDTO(City city) {
		this.id = city.getId();
		this.name = city.getName();
		this.stateDTO = new StateDTO(city.getState());
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

	public StateDTO getStateDTO() {
		return stateDTO;
	}

	public void setStateDTO(StateDTO stateDTO) {
		this.stateDTO = stateDTO;
	}

}
