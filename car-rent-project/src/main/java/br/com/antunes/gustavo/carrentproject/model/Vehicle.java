package br.com.antunes.gustavo.carrentproject.model;

import java.math.BigDecimal;
import java.util.Objects;

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
    private String modelYear;
    private String licensePlate;
    
    private BigDecimal dailyRentPrice;
    
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    
    public Vehicle() {}

	public Vehicle(Long id, String make, String model, String modelYear, String licensePlate, BigDecimal dailyRentPrice, VehicleType type) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.modelYear = modelYear;
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
		return modelYear;
	}

	public void setYear(String modelYear) {
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

	@Override
	public int hashCode() {
		return Objects.hash(dailyRentPrice, id, licensePlate, make, model, modelYear, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(dailyRentPrice, other.dailyRentPrice) && Objects.equals(id, other.id)
				&& Objects.equals(licensePlate, other.licensePlate) && Objects.equals(make, other.make)
				&& Objects.equals(model, other.model) && Objects.equals(modelYear, other.modelYear)
				&& type == other.type;
	}
}
