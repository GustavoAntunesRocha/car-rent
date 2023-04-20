package br.com.antunes.gustavo.carrentproject.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.antunes.gustavo.carrentproject.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Optional<Customer> findByFirstName(String name);

}
