package br.com.antunes.gustavo.carrentproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.City;
import br.com.antunes.gustavo.carrentproject.model.dto.CityDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CityService {
    
    private CityRepository cityRepository;

    private StateService stateService;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void deleteCity(Long id) {
        getCityById(id);
        cityRepository.deleteById(id);
    }

    public CityDTO createCity(CityDTO cityDTO) {
        City city = convertToEntity(cityDTO);
        cityRepository.save(city);
        return new CityDTO(city);
    }

    public CityDTO updateCity(CityDTO cityDTO) {
        City city = cityRepository.findById(cityDTO.getId()).orElseThrow(() -> new EntityNotFoundException("City not found with id " + cityDTO.getId()));
        cityRepository.save(city);
        return new CityDTO(city);
    }

    public CityDTO getCityById(Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City not found with id " + id));
        return new CityDTO(city);
    }

    public List<CityDTO> getAllCities() {
        List<City> cities = cityRepository.findAll();
        List<CityDTO> citiesDTO = new ArrayList<>();
        for (City city : cities) {
            citiesDTO.add(new CityDTO(city));
        }
        return citiesDTO;
    }

    public City convertToEntity (CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setName(cityDTO.getName());
        city.setState(stateService.convertToEntity(cityDTO.getStateDTO()));
        return city;
    }
}
