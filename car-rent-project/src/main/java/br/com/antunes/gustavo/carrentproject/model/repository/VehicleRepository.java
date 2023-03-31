package br.com.antunes.gustavo.carrentproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.antunes.gustavo.carrentproject.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

}
