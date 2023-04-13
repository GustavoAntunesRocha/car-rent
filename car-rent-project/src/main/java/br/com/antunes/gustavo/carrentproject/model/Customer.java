package br.com.antunes.gustavo.carrentproject.model;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Customer extends Person{
	
	private static final Logger logger = Logger.getLogger(Customer.class.getName());

	private String driverLicenceNumber;
	
	@OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(driverLicenceNumber, rentals);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		logger.info("Entering equals method in Customer class");
		if (this == obj) {
			logger.info("Exiting equals method in Customer class with true value");
			return true;
		}
		if (!super.equals(obj)) {
			logger.info("Exiting equals method in Customer class with false value");
			return false;
		}
		if (getClass() != obj.getClass()) {
			logger.info("Exiting equals method in Customer class with false value");
			return false;
		}
		logger.info("Exiting equals method in Customer class with true value");
		Customer other = (Customer) obj;
		return Objects.equals(driverLicenceNumber, other.driverLicenceNumber);
	}
	
}
