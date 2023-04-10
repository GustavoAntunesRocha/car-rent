package br.com.antunes.gustavo.carrentproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.antunes.gustavo.carrentproject.model.Vehicle;
import br.com.antunes.gustavo.carrentproject.model.dto.VehicleDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public VehicleDTO getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id " + id));
        return convertToDTO(vehicle);
    }

    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = convertToEntity(vehicleDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return convertToDTO(savedVehicle);
    }

    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findById(vehicleDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found with id " + vehicleDTO.getId()));

        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setModel(vehicleDTO.getModelYear());
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setDailyRentPrice(vehicleDTO.getDailyRentPrice());
        vehicle.setType(vehicleDTO.getType());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return convertToDTO(savedVehicle);
    }

    public void deleteVehicle(Long id) {
    	getVehicleById(id);
        vehicleRepository.deleteById(id);
    }

    private VehicleDTO convertToDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO(vehicle);
        return vehicleDTO;
    }

    public Vehicle convertToEntity(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDTO.getId());
        vehicle.setMake(vehicleDTO.getMake());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setModel(vehicleDTO.getModelYear());
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setDailyRentPrice(vehicleDTO.getDailyRentPrice());
        vehicle.setType(vehicleDTO.getType());
        return vehicle;
    }
}

