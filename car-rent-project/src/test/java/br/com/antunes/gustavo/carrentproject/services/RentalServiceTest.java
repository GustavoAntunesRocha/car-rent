package br.com.antunes.gustavo.carrentproject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import br.com.antunes.gustavo.carrentproject.model.Address;
import br.com.antunes.gustavo.carrentproject.model.City;
import br.com.antunes.gustavo.carrentproject.model.Country;
import br.com.antunes.gustavo.carrentproject.model.Customer;
import br.com.antunes.gustavo.carrentproject.model.Rental;
import br.com.antunes.gustavo.carrentproject.model.RentalStatus;
import br.com.antunes.gustavo.carrentproject.model.State;
import br.com.antunes.gustavo.carrentproject.model.Vehicle;
import br.com.antunes.gustavo.carrentproject.model.VehicleType;
import br.com.antunes.gustavo.carrentproject.model.dto.RentalDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.CityRepository;
import br.com.antunes.gustavo.carrentproject.model.repository.CustomerRepository;
import br.com.antunes.gustavo.carrentproject.model.repository.RentalRepository;
import br.com.antunes.gustavo.carrentproject.model.repository.VehicleRepository;
import br.com.antunes.gustavo.carrentproject.service.CustomerService;
import br.com.antunes.gustavo.carrentproject.service.RentalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@SpringBootTest
@ContextConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@WithUserDetails("admin@admin.com")
public class RentalServiceTest {
	
	@Autowired
	private RentalService rentalService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private CityRepository cityRepository;


	private Rental rental;

	private Rental rental2;

	private Customer customer;

	private Vehicle vehicle;

	
	
	@Order(0)
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

		City city = cityRepository
                .save(new City(1L, "Goiania", new State(1L, "Goias", "GO", new Country(1L, "Brasil", "55"))));
	    customer.setAddress(
	    		new Address(1L, "Rua 5", "740000", city ));
	    
	    LocalDate startDate = LocalDate.of(2023, 4, 12);
	    LocalDate endDate = LocalDate.of(2023, 4, 30);
	    BigDecimal totalPrice = vehicle.getDailyRentPrice().multiply(BigDecimal.valueOf(startDate.until(endDate, ChronoUnit.DAYS)));
	    
	    rental = new Rental(1L, customer, vehicle, startDate, endDate, totalPrice, RentalStatus.OPEN);
	    rental2 = new Rental(2L, customer, vehicle, startDate, endDate, totalPrice, RentalStatus.OPEN);
	    List<Rental> rentals = new ArrayList<>();
	    rentals.add(rental);
		rentals.add(rental2);
	    customer.setRentals(rentals);

		customerService.create(customerService.convertToDTO(customer), "123");
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

		assertEquals(rentals, rentalRepository.findAll());

	}

	@Test
	@Transactional
	public void findByCustomer() {

		rentalService.create(rentalService.convertToDTO(rental));
		rentalService.create(rentalService.convertToDTO(rental2));
		
		List<Long> rentalsIDs = new ArrayList<>();
		rentalsIDs.add(rental.getId());
		rentalsIDs.add(rental2.getId());
		
		List<RentalDTO> rentals = rentalService.findByCustomer(customer.getId());
		List<Long> rentalsIDs2 = new ArrayList<>();
		for (RentalDTO rentalDTO : rentals) {
			rentalsIDs2.add(rentalDTO.getId());
		}

		assertEquals(rentalsIDs, rentalsIDs2);

	}

}
