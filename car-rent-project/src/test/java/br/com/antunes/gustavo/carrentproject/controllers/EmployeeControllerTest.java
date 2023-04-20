package br.com.antunes.gustavo.carrentproject.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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
        this.mockMvc.perform(post("/employee").content(expectedJson).contentType("application/json"))
                .andExpect(status().isCreated());
    }

}
