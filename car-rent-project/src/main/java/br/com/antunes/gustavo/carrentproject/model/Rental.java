package br.com.antunes.gustavo.carrentproject.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Rental {
	
	private static final Logger logger = Logger.getLogger(Rental.class.getName());

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToOne
	private Vehicle vehicle;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private BigDecimal totalPrice;
	
	@Enumerated(EnumType.STRING)
	private RentalStatus rentalStatus;
	
	public Rental() {}

	public Rental(Long id, Customer customer, Vehicle vehicle, LocalDate startDate, LocalDate endDate, BigDecimal totalPrice, RentalStatus rentalStatus) {
		super();
		this.id = id;
		this.customer = customer;
		this.vehicle = vehicle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
		this.rentalStatus = rentalStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
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

	@Override
	public int hashCode() {
		return Objects.hash(customer, endDate, id, rentalStatus, startDate, totalPrice, vehicle);
	}

	@Override
	public boolean equals(Object obj) {
		logger.info("Entering equals method in Rental class");
		if (this == obj) {
			logger.info("Exiting equals method in Rental class with true value");
			return true;
		}
		if (obj == null) {
			logger.info("Exiting equals method in Rental class with false value");
			return false;
		}
		if (getClass() != obj.getClass()) {
			logger.info("Exiting equals method in Rental class with false value");
			return false;
		}
		logger.info("Exiting equals method in Rental class with true value");
		Rental other = (Rental) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(id, other.id) && rentalStatus == other.rentalStatus
				&& Objects.equals(startDate, other.startDate) && Objects.equals(totalPrice, other.totalPrice)
				&& Objects.equals(vehicle, other.vehicle);
	}

	public RentalStatus getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(RentalStatus rentalStatus) {
		this.rentalStatus = rentalStatus;
	}
	
}
