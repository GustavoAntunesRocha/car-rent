package br.com.antunes.gustavo.carrentproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.antunes.gustavo.carrentproject.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long>{

}
