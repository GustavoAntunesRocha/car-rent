package br.com.antunes.gustavo.carrentproject.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.antunes.gustavo.carrentproject.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

    Optional<Vehicle> findByModelContainingIgnoreCase(String model);

}
