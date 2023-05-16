package br.com.antunes.gustavo.carrentproject.services;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

import br.com.antunes.gustavo.carrentproject.model.VehicleType;
import br.com.antunes.gustavo.carrentproject.model.dto.VehicleDTO;
import br.com.antunes.gustavo.carrentproject.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
@WithUserDetails("admin@admin.com")
public class VehicleServiceTest {

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void testCreateVehicle() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setMake("Toyota");
        vehicleDTO.setModel("Corolla");
        vehicleDTO.setModelYear("2022");
        vehicleDTO.setLicensePlate("ABC123");
        vehicleDTO.setDailyRentPrice(new BigDecimal("50.00"));
        vehicleDTO.setType(VehicleType.CAR);


        VehicleDTO vehicleDTOSaved = vehicleService.createVehicle(vehicleDTO);

        assertNotNull(vehicleDTOSaved.getId());
    }
    
    @Test
    public void testGetVehicle() {
    	VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setMake("Toyota");
        vehicleDTO.setModel("Corolla");
        vehicleDTO.setModelYear("2022");
        vehicleDTO.setLicensePlate("ABC123");
        vehicleDTO.setDailyRentPrice(new BigDecimal("50.00"));
        vehicleDTO.setType(VehicleType.CAR);
        
        VehicleDTO addedVehicleDTO = vehicleService.createVehicle(vehicleDTO);
        
        VehicleDTO retrievedVehicleDTO = vehicleService.getVehicleById(addedVehicleDTO.getId());

        assertEquals(addedVehicleDTO, retrievedVehicleDTO);
    }
    

    @Test
    public void testUpdateVehicle() {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setMake("Toyota");
        vehicleDTO.setModel("Corolla");
        vehicleDTO.setModelYear("2020");
        vehicleDTO.setLicensePlate("ABC123");
        vehicleDTO.setDailyRentPrice(BigDecimal.valueOf(50));
        vehicleDTO.setType(VehicleType.CAR);

        VehicleDTO addedVehicleDTO = vehicleService.createVehicle(vehicleDTO);

        addedVehicleDTO.setMake("Honda");
        VehicleDTO updatedVehicleDTO = vehicleService.updateVehicle(addedVehicleDTO);

        assertEquals("Honda", updatedVehicleDTO.getMake());
    }

    @Test
    public void testDeleteVehicle() {
        // create a new vehicle
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setMake("Toyota");
        vehicleDTO.setModel("Corolla");
        vehicleDTO.setModelYear("2020");
        vehicleDTO.setLicensePlate("ABC123");
        vehicleDTO.setDailyRentPrice(BigDecimal.valueOf(50));
        vehicleDTO.setType(VehicleType.CAR);

        VehicleDTO addedVehicleDTO = vehicleService.createVehicle(vehicleDTO);

        vehicleService.deleteVehicle(addedVehicleDTO.getId());

        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> vehicleService.getVehicleById(addedVehicleDTO.getId()));
    }
}
