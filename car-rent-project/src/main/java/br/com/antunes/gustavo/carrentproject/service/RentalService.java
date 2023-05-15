package br.com.antunes.gustavo.carrentproject.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.Customer;
import br.com.antunes.gustavo.carrentproject.model.Rental;
import br.com.antunes.gustavo.carrentproject.model.RentalStatus;
import br.com.antunes.gustavo.carrentproject.model.Vehicle;
import br.com.antunes.gustavo.carrentproject.model.dto.RentalDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.RentalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class RentalService {

	private final RentalRepository rentalRepository;
	
	private final VehicleService vehicleService;
	
	private final CustomerService customerService;
	
	
	public RentalService(RentalRepository rentalRepository, VehicleService vehicleService, CustomerService customerService) {
		this.rentalRepository = rentalRepository;
		this.vehicleService = vehicleService;
		this.customerService = customerService;
	}
	
	public RentalDTO create(@Valid RentalDTO rentalDTO) throws EntityNotFoundException{
		rentalDTO.setRentalStatus(RentalStatus.OPEN);
		LocalDate startDate = rentalDTO.getStartDate();
	    LocalDate endDate = rentalDTO.getEndDate();
	    BigDecimal totalPrice = vehicleService.getVehicleById(rentalDTO.getVehicle().getId())
			.getDailyRentPrice().multiply(BigDecimal.valueOf(startDate.until(endDate, ChronoUnit.DAYS)));
		rentalDTO.setTotalPrice(totalPrice);
		Rental rental = rentalRepository.save(convertToEntity(rentalDTO));
		return convertToDTO(rental);
	}
	
	public RentalDTO update(@Valid RentalDTO rentalDTO) throws EntityNotFoundException{
		Rental rental = rentalRepository.findById(rentalDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + rentalDTO.getId()));
		rental = convertToEntity(rentalDTO);
		rentalRepository.save(rental);
		return convertToDTO(rental);
	}
	
	public RentalDTO findById(long id) {
		Rental rental = rentalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + id));
		return convertToDTO(rental);
	}

	public List<RentalDTO> findByCustomer(long customerId) {
		List<Rental> rentals = rentalRepository.findByCustomerId(customerId);
		if(rentals.isEmpty()) throw new EntityNotFoundException("Rental not found with customer id: " + customerId);
		List<RentalDTO> rentalDTOs = rentals.stream().map(rental -> convertToDTO(rental)).toList();
		return rentalDTOs;
	}

	public void delete(Long id) {
		rentalRepository.deleteById(id);
    }
	
	public RentalDTO convertToDTO(Rental rental) {
		return new RentalDTO(rental);
	}
	
	public Rental convertToEntity(@Valid RentalDTO rentalDTO) throws EntityNotFoundException{
		Vehicle vehicle = vehicleService.convertToEntity(vehicleService.getVehicleById(rentalDTO.getVehicle().getId()));
		Customer customer = customerService.convertToEntity(customerService.findById(rentalDTO.getCustomer().getId()));
		return new Rental(rentalDTO.getId(), customer, vehicle, rentalDTO.getStartDate(), rentalDTO.getEndDate(), rentalDTO.getTotalPrice(), RentalStatus.valueOf(rentalDTO.getRentalStatus()));
	}

    public List<RentalDTO> findAll() {
		List<Rental> rentals = rentalRepository.findAll();
		return rentals.stream().map(rental -> convertToDTO(rental)).toList();
    }

}
