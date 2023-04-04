package br.com.antunes.gustavo.carrentproject.model.dto;

import java.math.BigDecimal;

import br.com.antunes.gustavo.carrentproject.model.Employee;

public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private String firstName;
    private String lastName;
    private String identificationNumber;
    private AddressDTO address;

    private String jobTitle;
    private BigDecimal salary;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.identificationNumber = employee.getIdentificationNumber();
        this.address = new AddressDTO(employee.getAddress());

        this.jobTitle = employee.getJobTitle();
        this.salary = employee.getSalary();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

    
}
