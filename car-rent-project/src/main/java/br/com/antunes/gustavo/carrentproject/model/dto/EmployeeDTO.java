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

    // getters and setters omitted for brevity
}
