package br.com.antunes.gustavo.carrentproject.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Customer extends Person{

	private String driverLicenceNumber;
	
	@OneToMany(mappedBy = "customer")
	private List<Rental> rentals;
	
	public Customer() {}

	public Customer(String driverLicenceNumber, List<Rental> rentals) {
		super();
		this.driverLicenceNumber = driverLicenceNumber;
		this.rentals = rentals;
	}

	public String getDriverLicenceNumber() {
		return driverLicenceNumber;
	}

	public void setDriverLicenceNumber(String driverLicenceNumber) {
		this.driverLicenceNumber = driverLicenceNumber;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}
	
}
