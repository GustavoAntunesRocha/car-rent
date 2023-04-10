package br.com.antunes.gustavo.carrentproject.model.dto;

import java.util.List;

import br.com.antunes.gustavo.carrentproject.model.Customer;
import br.com.antunes.gustavo.carrentproject.model.Rental;

public class CustomerDTO {

	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private String identificationNumber;
	private AddressDTO address;

	private String driverLicenceNumber;
	
	private List<RentalDTO> rentals;

	public CustomerDTO() {
	}

	public CustomerDTO(Customer customer) {
		this.id = customer.getId();
		this.email = customer.getEmail();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.identificationNumber = customer.getIdentificationNumber();
		this.address = new AddressDTO(customer.getAddress());
		for (Rental rental : customer.getRentals()) {
			this.rentals.add(new RentalDTO(rental));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public String getDriverLicenceNumber() {
		return driverLicenceNumber;
	}

	public void setDriverLicenceNumber(String driverLicenceNumber) {
		this.driverLicenceNumber = driverLicenceNumber;
	}

	public List<RentalDTO> getRentals() {
		return rentals;
	}

	public void setRentals(List<RentalDTO> rentals) {
		this.rentals = rentals;
	}

}
