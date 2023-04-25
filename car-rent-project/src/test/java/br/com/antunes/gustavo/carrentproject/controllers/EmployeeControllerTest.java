package br.com.antunes.gustavo.carrentproject.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.antunes.gustavo.carrentproject.model.Address;
import br.com.antunes.gustavo.carrentproject.model.City;
import br.com.antunes.gustavo.carrentproject.model.Country;
import br.com.antunes.gustavo.carrentproject.model.State;
import br.com.antunes.gustavo.carrentproject.model.dto.AddressDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.EmployeeDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.CityRepository;
import br.com.antunes.gustavo.carrentproject.service.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private CityRepository cityRepository;

    private EmployeeDTO employeeDTO;

    private String jwt;

    @BeforeAll
    public void setUp() throws Exception {
        this.mockMvc.perform(post("/user/create")
        .content("{\"email\":\"teste@teste.com\",\"role\":\"ADMIN\"}")
        .param("password", "123")
        .contentType("application/json"))
                .andExpect(status().isCreated());
        this.mockMvc.perform(post("/user/login")
        .content("{\"email\":\"teste@teste.com\",\"password\":\"123\"}")
        .contentType("application/json"))
                .andExpect(status().isOk()).andDo(result -> {
                    JSONObject responseBody = new JSONObject(result.getResponse().getContentAsString());
                    this.jwt = responseBody.getString("accesToken");
                });
    }

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
        City city = cityRepository.save(new City(1L, "Goiania", new State(1L, "Goias", "GO", new Country(1L, "Brasil", "55"))));
        employeeDTO.setAddress(
                new AddressDTO(new Address(1L, "Rua 5", "740000",
                        city)));

    }

    @Test
    public void testCreateEmployee() throws Exception {
        String expectedJson = new ObjectMapper().writeValueAsString(this.employeeDTO);
        this.mockMvc.perform(post("/api/v1/employee")
        .content(expectedJson)
        .header("Authorization", "Bearer " + this.jwt)
        .contentType("application/json"))
                .andExpect(status().isCreated());
    }

}
