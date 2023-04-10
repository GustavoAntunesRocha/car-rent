package br.com.antunes.gustavo.carrentproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.Customer;
import br.com.antunes.gustavo.carrentproject.model.Rental;
import br.com.antunes.gustavo.carrentproject.model.Vehicle;
import br.com.antunes.gustavo.carrentproject.model.dto.CustomerDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.RentalDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	private final VehicleService vehicleService;
	
	private final AddressService addressService;

	public CustomerService(CustomerRepository customerRepository, VehicleService vehicleService, AddressService addressService) {
		this.customerRepository = customerRepository;
		this.vehicleService = vehicleService;
		this.addressService = addressService;
	}

	public CustomerDTO convertToDTO(Customer customer) {
		return new CustomerDTO(customer);
	}

	public Customer convertToEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setId(customerDTO.getId());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setEmail(customerDTO.getEmail());
		customer.setDriverLicenceNumber(customerDTO.getDriverLicenceNumber());
		customer.setIdentificationNumber(customerDTO.getIdentificationNumber());

		List<Rental> rentals = new ArrayList<>();
		if (customerDTO.getRentals() != null) {
			for (RentalDTO rentalDTO : customerDTO.getRentals()) {
				rentals.add(
						new Rental(rentalDTO.getId(), customer, vehicleService.convertToEntity(rentalDTO.getVehicle()),
								rentalDTO.getStartDate(), rentalDTO.getEndDate(), rentalDTO.getTotalPrice()));
			}
		}
		customer.setRentals(rentals);
		customer.setAddress(addressService.convertToEntity(customerDTO.getAddress()));
		
		return customer;
	}
}
