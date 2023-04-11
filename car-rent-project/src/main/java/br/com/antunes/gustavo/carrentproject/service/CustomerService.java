package br.com.antunes.gustavo.carrentproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.Customer;
import br.com.antunes.gustavo.carrentproject.model.Rental;
import br.com.antunes.gustavo.carrentproject.model.RentalStatus;
import br.com.antunes.gustavo.carrentproject.model.dto.CustomerDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.RentalDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;

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
	
	public CustomerDTO create(CustomerDTO customerDTO) {
		return convertToDTO(customerRepository.save(convertToEntity(customerDTO)));
	}
	
	public CustomerDTO update(CustomerDTO customerDTO) {
		Customer customer = customerRepository.findById(customerDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerDTO.getId()));
		customer = convertToEntity(customerDTO);
		customerRepository.save(customer);
		return convertToDTO(customer);
	}
	
	public void delete(long id) {
		findById(id);
		customerRepository.deleteById(id);
	}
	
	public CustomerDTO findById(long id) throws EntityNotFoundException{
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
		return convertToDTO(customer);
	}

	public CustomerDTO convertToDTO(Customer customer) {
		return new CustomerDTO(customer);
	}

	public Customer convertToEntity(CustomerDTO customerDTO) throws EntityNotFoundException{
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
								rentalDTO.getStartDate(), rentalDTO.getEndDate(), rentalDTO.getTotalPrice(), RentalStatus.valueOf(rentalDTO.getRentalStatus())));
			}
		}
		customer.setRentals(rentals);
		customer.setAddress(addressService.convertToEntity(customerDTO.getAddress()));
		
		return customer;
	}
}
