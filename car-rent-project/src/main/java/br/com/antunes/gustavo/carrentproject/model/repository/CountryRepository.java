package br.com.antunes.gustavo.carrentproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.antunes.gustavo.carrentproject.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
