package br.com.antunes.gustavo.carrentproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class State {

	private String name;
    private String abbreviation;
    
    @ManyToOne
    private Country country;
    
    public State() {}

	public State(String name, String abbreviation, Country country) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
		this.country = country;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
    
    
}
