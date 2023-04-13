package br.com.antunes.gustavo.carrentproject.model;

import java.util.Objects;
import java.util.logging.Logger;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {
	
	private static final Logger logger = Logger.getLogger(Address.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String street;

	private String zipCode;

	@ManyToOne(cascade = CascadeType.ALL)
	private City city;

	public Address() {
	}

	public Address(Long id, String street, String zipCode, City city) {
		super();
		this.id = id;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
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

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, id, street, zipCode);
	}

	@Override
	public boolean equals(Object obj) {
		logger.info("Entering equals method in Address class");
		if (this == obj) {
			logger.info("Exiting equals method in Address class with true value");
			return true;
		}
		if (obj == null) {
			logger.info("Exiting equals method in Address class with false value");
			return false;
		}
		if (getClass() != obj.getClass()) {
			logger.info("Exiting equals method in Address class with false value");
			return false;
		}
		logger.info("Exiting equals method in Address class with true value");
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(id, other.id) && Objects.equals(street, other.street)
				&& Objects.equals(zipCode, other.zipCode);
	}

}
