package br.com.antunes.gustavo.carrentproject.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.antunes.gustavo.carrentproject.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long>{

    List<Rental> findByCustomerId(long customerId);

}
