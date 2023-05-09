package br.com.antunes.gustavo.carrentproject.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.antunes.gustavo.carrentproject.controller.EmployeeController;
import br.com.antunes.gustavo.carrentproject.controller.LoginRequest;
import br.com.antunes.gustavo.carrentproject.controller.LoginResponse;
import br.com.antunes.gustavo.carrentproject.exception.RestExceptionHandler;
import br.com.antunes.gustavo.carrentproject.model.Address;
import br.com.antunes.gustavo.carrentproject.model.City;
import br.com.antunes.gustavo.carrentproject.model.Country;
import br.com.antunes.gustavo.carrentproject.model.State;
import br.com.antunes.gustavo.carrentproject.model.dto.AddressDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.EmployeeDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.CityRepository;
import br.com.antunes.gustavo.carrentproject.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepository cityRepository;

    @Mock
    private EmployeeController employeeController;

    @Autowired
    private UserService userService;

    private EmployeeDTO employeeDTO;

    private String jwt;

    record createEmployeeRequest(EmployeeDTO employeeDTO, String password) {
    }

    @Order(2)
    @BeforeAll
    public void setUp() throws Exception {

        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .setControllerAdvice(new RestExceptionHandler()).build();

        String email = "admin@admin.com";
        String password = "admin";
        LoginResponse loginResponse = userService.authenticate(new LoginRequest(email, password));
        this.jwt = loginResponse.getAccesToken();
    }

    @Order(1)
    @BeforeEach
    public void setUpValidEmployee() {
        employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setFirstName("Joao");
        employeeDTO.setLastName("Silva");
        employeeDTO.setEmail("teste@teste.com");
        employeeDTO.setIdentificationNumber("123");
        employeeDTO.setJobTitle("Sales manager");
        employeeDTO.setSalary(BigDecimal.valueOf(2500));
        City city = cityRepository
                .save(new City(1L, "Goiania", new State(1L, "Goias", "GO", new Country(1L, "Brasil", "55"))));
        employeeDTO.setAddress(
                new AddressDTO(new Address(1L, "Rua 5", "740000",
                        city)));

    }

    @Test
    public void testCreateEmployee() throws Exception {

        String expectedJson = new ObjectMapper().writeValueAsString(new createEmployeeRequest(employeeDTO, "admin"));
        this.mockMvc.perform(post("/api/v1/employee")
                .content(expectedJson)
                .header("Authorization", "Bearer " + this.jwt)
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateEmployeeWithoutAuthorization() throws Exception {
        String expectedJson = new ObjectMapper().writeValueAsString(new createEmployeeRequest(employeeDTO, "admin"));
        this.mockMvc.perform(post("/api/v1/employee")
                .content(expectedJson)
                .contentType("application/json"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testUpdateEmployeeShouldBeTrue() throws Exception {
        String originalJson = new ObjectMapper().writeValueAsString(new createEmployeeRequest(employeeDTO, "admin"));
        this.mockMvc.perform(post("/api/v1/employee")
                .content(originalJson)
                .header("Authorization", "Bearer " + this.jwt)
                .contentType("application/json"));

        this.employeeDTO.setFirstName("Jose");
        String updatedJson = new ObjectMapper().writeValueAsString(new createEmployeeRequest(employeeDTO, "admin"));
        this.mockMvc.perform(put("/api/v1/employee")
                .content(updatedJson)
                .header("Authorization", "Bearer " + this.jwt)
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEmployeeShouldBeTrue() throws Exception {
        String originalJson = new ObjectMapper().writeValueAsString(new createEmployeeRequest(employeeDTO, "admin"));
        this.mockMvc.perform(post("/api/v1/employee")
                .content(originalJson)
                .header("Authorization", "Bearer " + this.jwt)
                .contentType("application/json"))
                .andExpect(status().isCreated());

        this.mockMvc.perform(delete("/api/v1/employee")
                .header("Authorization", "Bearer " + this.jwt)
                .param("id", 1L + "")
                .contentType("application/json"))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/api/v1/employee")
                .header("Authorization", "Bearer " + this.jwt)
                .param("id", 6L + "")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

}
