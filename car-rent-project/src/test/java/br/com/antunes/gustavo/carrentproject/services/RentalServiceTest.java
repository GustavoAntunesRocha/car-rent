package br.com.antunes.gustavo.carrentproject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import br.com.antunes.gustavo.carrentproject.model.Address;
import br.com.antunes.gustavo.carrentproject.model.City;
import br.com.antunes.gustavo.carrentproject.model.Country;
import br.com.antunes.gustavo.carrentproject.model.Customer;
import br.com.antunes.gustavo.carrentproject.model.Rental;
import br.com.antunes.gustavo.carrentproject.model.RentalStatus;
import br.com.antunes.gustavo.carrentproject.model.State;
import br.com.antunes.gustavo.carrentproject.model.Vehicle;
import br.com.antunes.gustavo.carrentproject.model.VehicleType;
import br.com.antunes.gustavo.carrentproject.model.repository.CustomerRepository;
import br.com.antunes.gustavo.carrentproject.model.repository.RentalRepository;
import br.com.antunes.gustavo.carrentproject.model.repository.VehicleRepository;
import br.com.antunes.gustavo.carrentproject.service.RentalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RentalServiceTest {
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;


	private Rental rental;

	private Customer customer;

	private Vehicle vehicle;

	
	
	@BeforeEach
	@Transactional
	public void createEntity() {
		vehicle = new Vehicle();
        vehicle.setMake("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setModel("2022");
        vehicle.setLicensePlate("ABC123");
        vehicle.setDailyRentPrice(new BigDecimal("50.00"));
        vehicle.setType(VehicleType.CAR);
        
        customer = new Customer();
	    customer.setId(1L);
	    customer.setFirstName("Joao");
	    customer.setLastName("Silva");
	    customer.setEmail("teste@teste.com");
	    customer.setIdentificationNumber("123");
	    customer.setDriverLicenceNumber("123456789");
	    customer.setAddress(
	    		new Address(1L, "Rua 5", "740000", new City(1L, "Goiania", new State(1L, "Goias", "GO", new Country(1L, "Brasil", "55"))))
	    		);
	    
	    LocalDate startDate = LocalDate.of(2023, 4, 12);
	    LocalDate endDate = LocalDate.of(2023, 4, 30);
	    BigDecimal totalPrice = vehicle.getDailyRentPrice().multiply(BigDecimal.valueOf(startDate.until(endDate, ChronoUnit.DAYS)));
	    
	    rental = new Rental(1L, customer, vehicle, startDate, endDate, totalPrice, RentalStatus.OPEN);
	    
	    List<Rental> rentals = new ArrayList<>();
	    rentals.add(rental);
	    customer.setRentals(rentals);

		customerRepository.save(rental.getCustomer());
		vehicleRepository.save(rental.getVehicle());
	    
	}
	
	@Test
	@Transactional
	public void save() {
		
		
		rentalService.create(rentalService.convertToDTO(rental));

		Rental retrievedRental = rentalService.convertToEntity(rentalService.findById(rental.getId()));
		retrievedRental.setCustomer(customerRepository.findById(retrievedRental.getCustomer().getId()).orElseThrow());
		
		assertEquals(rental, retrievedRental);

	}

	@Test
	@Transactional
	public void update() {

		rentalService.create(rentalService.convertToDTO(rental));
		
		Rental retrievedRental = rentalService.convertToEntity(rentalService.findById(rental.getId()));
		retrievedRental.setCustomer(customerRepository.findById(retrievedRental.getCustomer().getId()).orElseThrow());
		
		retrievedRental.setEndDate(LocalDate.of(2023, 5, 30));
		retrievedRental.setTotalPrice(retrievedRental.getVehicle().getDailyRentPrice().multiply(BigDecimal.valueOf(retrievedRental.getStartDate().until(retrievedRental.getEndDate(), ChronoUnit.DAYS))));
		
		rentalService.update(rentalService.convertToDTO(retrievedRental));
		
		Rental updatedRental = rentalService.convertToEntity(rentalService.findById(retrievedRental.getId()));
		updatedRental.setCustomer(customerRepository.findById(updatedRental.getCustomer().getId()).orElseThrow());
		
		assertEquals(retrievedRental, updatedRental);
	}

	@Test
	@Transactional
	public void delete() {
		
		rentalService.create(rentalService.convertToDTO(rental));
		
		Rental retrievedRental = rentalService.convertToEntity(rentalService.findById(rental.getId()));
		retrievedRental.setCustomer(customerRepository.findById(retrievedRental.getCustomer().getId()).orElseThrow());
		
		rentalService.delete(retrievedRental.getId());

		assertThrows(EntityNotFoundException.class, () -> rentalService.findById(retrievedRental.getId()));
	}

	@Test
	@Transactional
	public void findById() {

		rentalService.create(rentalService.convertToDTO(rental));
		
		Rental retrievedRental = rentalService.convertToEntity(rentalService.findById(rental.getId()));
		retrievedRental.setCustomer(customerRepository.findById(retrievedRental.getCustomer().getId()).orElseThrow());
		
		assertEquals(rental, retrievedRental);
	}

	@Test
	@Transactional
	public void findAll() {

		rentalService.create(rentalService.convertToDTO(rental));
		
		Rental retrievedRental = rentalService.convertToEntity(rentalService.findById(rental.getId()));
		retrievedRental.setCustomer(customerRepository.findById(retrievedRental.getCustomer().getId()).orElseThrow());
		
		List<Rental> rentals = new ArrayList<>();
		rentals.add(retrievedRental);
		
		assertEquals(rentals, rentalService.findAll());
	}

}
