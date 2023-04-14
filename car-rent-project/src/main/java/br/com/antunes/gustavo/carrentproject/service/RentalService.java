package br.com.antunes.gustavo.carrentproject.service;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.Customer;
import br.com.antunes.gustavo.carrentproject.model.Rental;
import br.com.antunes.gustavo.carrentproject.model.RentalStatus;
import br.com.antunes.gustavo.carrentproject.model.Vehicle;
import br.com.antunes.gustavo.carrentproject.model.dto.RentalDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.RentalRepository;
import jakarta.persistence.EntityNotFoundException;

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
	
	public RentalDTO create(RentalDTO rentalDTO) throws EntityNotFoundException{
		Rental rental = rentalRepository.save(convertToEntity(rentalDTO));
		return convertToDTO(rental);
	}
	
	public RentalDTO update(RentalDTO rentalDTO) throws EntityNotFoundException{
		Rental rental = rentalRepository.findById(rentalDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + rentalDTO.getId()));
		rental = convertToEntity(rentalDTO);
		rentalRepository.save(rental);
		return convertToDTO(rental);
	}
	
	public RentalDTO findById(long id) {
		Rental rental = rentalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + id));
		return convertToDTO(rental);
	}

	public void delete(Long id) {
		rentalRepository.deleteById(id);
    }
	
	public RentalDTO convertToDTO(Rental rental) {
		return new RentalDTO(rental);
	}
	
	public Rental convertToEntity(RentalDTO rentalDTO) throws EntityNotFoundException{
		Vehicle vehicle = vehicleService.convertToEntity(rentalDTO.getVehicle());
		Customer customer = customerService.convertToEntity(rentalDTO.getCustomer());
		return new Rental(rentalDTO.getId(), customer, vehicle, rentalDTO.getStartDate(), rentalDTO.getEndDate(), rentalDTO.getTotalPrice(), RentalStatus.valueOf(rentalDTO.getRentalStatus()));
	}

    public Object findAll() {
        return rentalRepository.findAll();
    }

}
