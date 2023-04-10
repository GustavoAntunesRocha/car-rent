package br.com.antunes.gustavo.carrentproject.service;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.Rental;
import br.com.antunes.gustavo.carrentproject.model.dto.RentalDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.RentalRepository;

@Service
public class RentalService {

	private final RentalRepository rentalRepository;
	
	public RentalService(RentalRepository rentalRepository) {
		this.rentalRepository = rentalRepository;
	}
	
	public RentalDTO convertToDTO(Rental rental) {
		return new RentalDTO(rental);
	}
}
