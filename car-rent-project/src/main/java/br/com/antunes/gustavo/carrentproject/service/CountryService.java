package br.com.antunes.gustavo.carrentproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.Country;
import br.com.antunes.gustavo.carrentproject.model.dto.CountryDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryDTO createCountry(CountryDTO countryDTO) {
        Country country = convertToEntity(countryDTO);
        countryRepository.save(country);
        return new CountryDTO(country);
    }

    public CountryDTO updateCountry(CountryDTO countryDTO) {
        Country country = countryRepository.findById(countryDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id " + countryDTO.getId()));
        countryRepository.save(country);
        return new CountryDTO(country);
    }

    public CountryDTO getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id " + id));
        return new CountryDTO(country);
    }

    public List<CountryDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDTO> countriesDTO = new ArrayList<>();
        for (Country country : countries) {
            countriesDTO.add(new CountryDTO(country));
        }
        return countriesDTO;
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }

    public CountryDTO convertToDTO(Country country) {
        CountryDTO countryDTO = new CountryDTO(country);
        return countryDTO;
    }

    public Country convertToEntity(CountryDTO countryDTO) {
        Country country = new Country();
        country.setId(countryDTO.getId());
        country.setName(countryDTO.getName());
        return country;
    }
}
