package br.com.antunes.gustavo.carrentproject.model.dto;

import java.math.BigDecimal;

import br.com.antunes.gustavo.carrentproject.model.Vehicle;
import br.com.antunes.gustavo.carrentproject.model.VehicleType;

public class VehicleDTO {

    private Long id;
    private String make;
    private String model;
    private String modelYear;
    private String licensePlate;
    private BigDecimal dailyRentPrice;
    private VehicleType type;

    public VehicleDTO() {
    }

    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.make = vehicle.getMake();
        this.model = vehicle.getModel();
        this.modelYear = vehicle.getYear();
        this.licensePlate = vehicle.getLicensePlate();
        this.dailyRentPrice = vehicle.getDailyRentPrice();
        this.type = vehicle.getType();
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public BigDecimal getDailyRentPrice() {
        return dailyRentPrice;
    }

    public void setDailyRentPrice(BigDecimal dailyRentPrice) {
        this.dailyRentPrice = dailyRentPrice;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}

