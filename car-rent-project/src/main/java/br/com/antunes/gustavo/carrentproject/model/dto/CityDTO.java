package br.com.antunes.gustavo.carrentproject.model.dto;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(id, name, stateDTO);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityDTO other = (CityDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(stateDTO, other.stateDTO);
	}

}
