package br.com.antunes.gustavo.carrentproject.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private String year;
    private String licensePlate;
    
    private BigDecimal dailyRentPrice;
    
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    
    public Vehicle() {}

	public Vehicle(Long id, String make, String model, String year, String licensePlate, BigDecimal dailyRentPrice, VehicleType type) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.year = year;
		this.licensePlate = licensePlate;
		this.dailyRentPrice = dailyRentPrice;
		this.type = type;
	}

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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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
