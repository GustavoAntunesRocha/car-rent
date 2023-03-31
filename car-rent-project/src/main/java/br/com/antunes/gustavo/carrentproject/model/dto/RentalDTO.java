package br.com.antunes.gustavo.carrentproject.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.antunes.gustavo.carrentproject.model.Rental;

public class RentalDTO {
    
    private Long id;
    
    private CustomerDTO customer;
    
    private VehicleDTO vehicle;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    private BigDecimal totalPrice;
    
    // Constructors, getters and setters
    
    public RentalDTO() {}
    
    public RentalDTO(Rental rental) {
        this.id = rental.getId();
        this.customer = new CustomerDTO(rental.getCustomer());
        this.vehicle = new VehicleDTO(rental.getVehicle());
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.totalPrice = rental.getTotalPrice();
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
}

