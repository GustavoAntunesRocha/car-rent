package br.com.antunes.gustavo.carrentproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.antunes.gustavo.carrentproject.model.dto.VehicleDTO;
import br.com.antunes.gustavo.carrentproject.service.VehicleService;

@RestController
@RequestMapping(value = "/api/v1/vehicle")
public class VehicleController {
    
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<?> getVehicles(@RequestParam (required = false) Long id, @RequestParam (required = false) String model) {
        if (id != null) {
            return ResponseEntity.ok(vehicleService.getVehicleById(id));
        } else if (model != null) {
            return ResponseEntity.ok(vehicleService.getVehicleByModel(model));
        } else {
            return ResponseEntity.ok(vehicleService.getAllVehicles());
        }
    }

    @PostMapping
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.created(null).body(vehicleService.createVehicle(vehicleDTO));
    }

    @PutMapping
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleDTO vehicleDTO) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleDTO));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteVehicle(@RequestParam Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }
}
