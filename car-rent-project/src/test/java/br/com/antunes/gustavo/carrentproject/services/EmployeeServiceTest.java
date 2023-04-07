package br.com.antunes.gustavo.carrentproject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.antunes.gustavo.carrentproject.model.Address;
import br.com.antunes.gustavo.carrentproject.model.City;
import br.com.antunes.gustavo.carrentproject.model.Country;
import br.com.antunes.gustavo.carrentproject.model.Employee;
import br.com.antunes.gustavo.carrentproject.model.State;
import br.com.antunes.gustavo.carrentproject.model.dto.EmployeeDTO;
import br.com.antunes.gustavo.carrentproject.service.EmployeeService;

@SpringBootTest
public class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
		
	@Test
	public void testConvert() {
	    Employee employee = new Employee();
	    employee.setId(1L);
	    employee.setFirstName("Joao");
	    employee.setLastName("Silva");
	    employee.setEmail("teste@teste.com");
	    employee.setIdentificationNumber("123");
	    employee.setJobTitle("Sales manager");
	    employee.setSalary(BigDecimal.valueOf(2500));
	    employee.setAddress(
	    		new Address(1L, "Rua 5", "740000", new City(1L, "Goiania", new State(1L, "Goias", "GO", new Country(1L, "Brasil", "55"))))
	    		);	    
	    
	    EmployeeDTO convertedEmployeeDTO = employeeService.convertToDTO(employee);
	    
	    assertEquals(employee.getId(), convertedEmployeeDTO.getId());
	    assertEquals(employee.getFirstName(), convertedEmployeeDTO.getFirstName());
	    assertEquals(employee.getLastName(), convertedEmployeeDTO.getLastName());
	    assertEquals(employee.getEmail(), convertedEmployeeDTO.getEmail());
	    assertEquals(employee.getIdentificationNumber(), convertedEmployeeDTO.getIdentificationNumber());
	    assertEquals(employee.getJobTitle(), convertedEmployeeDTO.getJobTitle());
	    assertEquals(employee.getSalary(), convertedEmployeeDTO.getSalary());
	    assertEquals(employee.getAddress().getCity().getState().getCountry().getName(), convertedEmployeeDTO.getAddress().getCityDTO().getStateDTO().getCountryDTO().getName());
	}

}
