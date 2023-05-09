package br.com.antunes.gustavo.carrentproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.exception.CustomException;
import br.com.antunes.gustavo.carrentproject.model.Customer;
import br.com.antunes.gustavo.carrentproject.model.Rental;
import br.com.antunes.gustavo.carrentproject.model.RentalStatus;
import br.com.antunes.gustavo.carrentproject.model.dto.CustomerDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.RentalDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.UserDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.CustomerRepository;
import br.com.antunes.gustavo.carrentproject.security.Role;
import br.com.antunes.gustavo.carrentproject.security.UserEntity;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	private final VehicleService vehicleService;
	
	private final AddressService addressService;

	private final UserService userService;

	public CustomerService(CustomerRepository customerRepository, VehicleService vehicleService, AddressService addressService, UserService userService) {
		this.customerRepository = customerRepository;
		this.vehicleService = vehicleService;
		this.addressService = addressService;
		this.userService = userService;
	}
	
	public CustomerDTO create(CustomerDTO customerDTO, String password) {
		try {
			UserDTO userDTO = new UserDTO();
			userDTO.setEmail(customerDTO.getEmail());
			userDTO.setRoles(Arrays.asList(Role.CLIENT));
			UserEntity user = userService.createUser(userDTO, password);
			Customer customer = convertToEntity(customerDTO);
			customer.setUserEntity(user);
			return convertToDTO(customerRepository.save(customer));
		} catch (DataIntegrityViolationException ex) {
			Throwable cause = ex.getCause();
			if (cause instanceof ConstraintViolationException) {
				ConstraintViolationException cve = (ConstraintViolationException) cause;
				if (cve.getConstraintName().contains("EMAIL")) {
					throw new CustomException("The email " + customerDTO.getEmail() + " is already in use.");
				} else if (cve.getConstraintName().contains("IDENTIFICATION_NUMBER")) {
					throw new CustomException("The identification number " + customerDTO.getIdentificationNumber() + " is already in use.");
				} else if (cve.getConstraintName().contains("DRIVER")) {
					throw new CustomException("The driver licence number " + customerDTO.getDriverLicenceNumber() + " is already in use.");
				}
			}
		}
		return null;
	}
	
	public CustomerDTO update(CustomerDTO customerDTO) {
		Customer customer = checkAuthorizationCustomer(customerDTO.getId());
		customer = convertToEntity(customerDTO);
		customerRepository.save(customer);
		return convertToDTO(customer);
	}
	
	public void delete(long id) {
		checkAuthorizationCustomer(id);
		customerRepository.deleteById(id);
	}
	
	public CustomerDTO findById(long id) throws EntityNotFoundException{
		Customer customer = checkAuthorizationCustomer(id);
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

	@Secured("EMPLOYEE")
    public CustomerDTO findByName(String name) {
		Customer customer = customerRepository.findByFirstName(name).orElseThrow(() -> new EntityNotFoundException("Customer not found with name: " + name));
		checkAuthorizationCustomer(customer.getId());
        return convertToDTO(customer);
    }

	@Secured("EMPLOYEE")
    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		for (Customer customer : customers) {
			customerDTOs.add(convertToDTO(customer));
		}
		return customerDTOs;
    }

	private Customer checkAuthorizationCustomer(long id) {
		UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
		if(userEntity.getId() != customer.getUserEntity().getId() && !userEntity.getRoles().contains(Role.ADMIN)){
			throw new CustomException("You are not allowed to access this resource.");
		}
		return customer;
	}
}
