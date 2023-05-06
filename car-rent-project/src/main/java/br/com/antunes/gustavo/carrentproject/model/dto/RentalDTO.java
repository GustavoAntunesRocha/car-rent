package br.com.antunes.gustavo.carrentproject.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.antunes.gustavo.carrentproject.model.Rental;
import br.com.antunes.gustavo.carrentproject.model.RentalStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class RentalDTO {
    
    private Long id;
    
    @Valid
    @NotNull
    private CustomerDTO customer;
    
    @Valid
    @NotNull
    private VehicleDTO vehicle;
    
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy")
    private LocalDate startDate;
    
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy")
    private LocalDate endDate;
    
    private BigDecimal totalPrice;
    
    private String rentalStatus;
    
    // Constructors, getters and setters
    
    public RentalDTO() {}
    
    public RentalDTO(Rental rental) {
        this.id = rental.getId();
        this.customer = new CustomerDTO(rental.getCustomer());
        this.vehicle = new VehicleDTO(rental.getVehicle());
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.totalPrice = rental.getTotalPrice();
        this.rentalStatus = rental.getRentalStatus().name();
    }
    
    // Getters and setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public CustomerDTO getCustomer() {
        return customer;
    }
    
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
    
    public VehicleDTO getVehicle() {
        return vehicle;
    }
    
    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

	public String getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(RentalStatus rentalStatus) {
		this.rentalStatus = rentalStatus.name();
	}
}

