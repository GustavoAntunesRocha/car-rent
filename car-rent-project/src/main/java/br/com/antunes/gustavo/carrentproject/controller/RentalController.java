package br.com.antunes.gustavo.carrentproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.antunes.gustavo.carrentproject.model.dto.RentalDTO;
import br.com.antunes.gustavo.carrentproject.service.RentalService;

@RestController
@RequestMapping(value = "/api/v1/rental")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<?> getRentals(@RequestParam(required = false) Long id, @RequestParam(required = false) Long customerId) {
        if (id != null) {
            return ResponseEntity.ok(rentalService.findById(id));
        } else if (customerId != null) {
            return ResponseEntity.ok(rentalService.findByCustomer(customerId));
        } else {
            return ResponseEntity.ok(rentalService.findAll());
        }
    }

    @PostMapping
    public ResponseEntity<?> createRental(@RequestBody RentalDTO rentalDTO) {
        return ResponseEntity.created(null).body(rentalService.create(rentalDTO));
    }
    
    
}
