package br.com.antunes.gustavo.carrentproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.antunes.gustavo.carrentproject.model.dto.CityDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.CountryDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.StateDTO;
import br.com.antunes.gustavo.carrentproject.service.CityService;
import br.com.antunes.gustavo.carrentproject.service.CountryService;
import br.com.antunes.gustavo.carrentproject.service.StateService;

@RestController
@RequestMapping(value = "/api/v1/location")
public class LocationController {
    
    private CityService cityService;

    private StateService stateService;

    private CountryService countryService;

    public LocationController(CityService cityService, StateService stateService, CountryService countryService) {
        this.cityService = cityService;
        this.stateService = stateService;
        this.countryService = countryService;
    }

    @PostMapping(value = "/city")
    public ResponseEntity<?> createCity(@RequestBody CityDTO cityDTO) {
        return ResponseEntity.ok(cityService.createCity(cityDTO));
    }

    @PostMapping(value = "/state")
    public ResponseEntity<?> createState(@RequestBody StateDTO stateDTO) {
        return ResponseEntity.ok(stateService.createState(stateDTO));
    }

    @PostMapping(value = "/country")
    public ResponseEntity<?> createCountry(@RequestBody CountryDTO countryDTO) {
        return ResponseEntity.ok(countryService.createCountry(countryDTO));
    }

}
