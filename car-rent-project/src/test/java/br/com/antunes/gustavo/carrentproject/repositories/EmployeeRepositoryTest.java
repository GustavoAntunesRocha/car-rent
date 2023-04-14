package br.com.antunes.gustavo.carrentproject.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import br.com.antunes.gustavo.carrentproject.model.Address;
import br.com.antunes.gustavo.carrentproject.model.City;
import br.com.antunes.gustavo.carrentproject.model.Country;
import br.com.antunes.gustavo.carrentproject.model.Employee;
import br.com.antunes.gustavo.carrentproject.model.State;
import br.com.antunes.gustavo.carrentproject.model.repository.EmployeeRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@AfterEach
	public void cleanUp() {
		employeeRepository.deleteAll();
	}

	@Test
	@Transactional
	public void testSave() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setFirstName("Joao");
		employee.setLastName("Silva");
		employee.setEmail("teste@teste.com");
		employee.setIdentificationNumber("123");
		employee.setJobTitle("Sales manager");
		employee.setSalary(BigDecimal.valueOf(2500));
		employee.setAddress(new Address(1L, "Rua 5", "740000",
				new City(1L, "Goiania", new State(1L, "Goias", "GO", new Country(1L, "Brasil", "55")))));

		Employee retrievedEmployee = employeeRepository.save(employee);

		assertEquals(employee, retrievedEmployee);
	}

	@Test
	public void testUpdate() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setFirstName("Joao");
		employee.setLastName("Silva");
		employee.setEmail("teste@teste.com");
		employee.setIdentificationNumber("123");
		employee.setJobTitle("Sales manager");
		employee.setSalary(BigDecimal.valueOf(2500));
		employee.setAddress(new Address(1L, "Rua 5", "740000",
				new City(1L, "Goiania", new State(1L, "Goias", "GO", new Country(1L, "Brasil", "55")))));

		Employee retrievedEmployee = employeeRepository.save(employee);

		retrievedEmployee.setFirstName("Gustavo");
		retrievedEmployee.setLastName("Antunes");
		retrievedEmployee.setEmail("teste2@teste2.com");
		retrievedEmployee.setIdentificationNumber("128883");
		retrievedEmployee.setJobTitle("Java Developer");
		retrievedEmployee.setSalary(BigDecimal.valueOf(4000));

		Employee updatedEmployee = employeeRepository.save(retrievedEmployee);

		assertEquals(retrievedEmployee, updatedEmployee);
	}

	public void testDelete() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setFirstName("Joao");
		employee.setLastName("Silva");
		employee.setEmail("teste@teste.com");
		employee.setIdentificationNumber("123");
		employee.setJobTitle("Sales manager");
		employee.setSalary(BigDecimal.valueOf(2500));
		employee.setAddress(new Address(1L, "Rua 5", "740000",
				new City(1L, "Goiania", new State(1L, "Goias", "GO", new Country(1L, "Brasil", "55")))));

		Employee retrievedEmployee = employeeRepository.save(employee);

		employeeRepository.delete(retrievedEmployee);

		assertNull(employeeRepository.findById(retrievedEmployee.getId()));
	}
}
