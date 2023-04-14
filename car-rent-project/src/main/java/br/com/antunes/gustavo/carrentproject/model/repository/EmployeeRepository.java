package br.com.antunes.gustavo.carrentproject.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.antunes.gustavo.carrentproject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    Optional<Employee> findByFirstName(String name);

}
