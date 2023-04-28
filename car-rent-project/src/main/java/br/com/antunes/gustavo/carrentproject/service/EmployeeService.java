package br.com.antunes.gustavo.carrentproject.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.antunes.gustavo.carrentproject.exception.CustomException;
import br.com.antunes.gustavo.carrentproject.model.Employee;
import br.com.antunes.gustavo.carrentproject.model.dto.EmployeeDTO;
import br.com.antunes.gustavo.carrentproject.model.dto.UserDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.EmployeeRepository;
import br.com.antunes.gustavo.carrentproject.security.Role;
import br.com.antunes.gustavo.carrentproject.security.UserEntity;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	private final ObjectMapper objectMapper;
	
	private final AddressService addressService;

    private final UserService userService;
	
	public EmployeeService(EmployeeRepository employeeRepository, ObjectMapper objectMapper, AddressService addressService, UserService userService) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
        this.addressService = addressService;
        this.userService = userService;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));
        return convertToDTO(employee);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO, String password) {
        try {
			UserDTO userDTO = new UserDTO();
			userDTO.setEmail(employeeDTO.getEmail());
			userDTO.setRoles(Arrays.asList(Role.EMPLOYEE));
			UserEntity user = userService.createUser(userDTO, password);
			Employee employee = convertToEntity(employeeDTO);
			employee.setUserEntity(user);
			return convertToDTO(employeeRepository.save(employee));
		} catch (DataIntegrityViolationException ex) {
			Throwable cause = ex.getCause();
			if (cause instanceof ConstraintViolationException) {
				ConstraintViolationException cve = (ConstraintViolationException) cause;
				if (cve.getConstraintName().contains("EMAIL")) {
					throw new CustomException("The email " + employeeDTO.getEmail() + " is already in use.");
				} else if (cve.getConstraintName().contains("IDENTIFICATION_NUMBER")) {
					throw new CustomException("The identification number " + employeeDTO.getIdentificationNumber() + " is already in use.");
				}
			}
		}
        return null;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + employeeDTO.getId()));

        if(employeeDTO.getAddress() == null){
        	employeeDTO.setAddress(convertToDTO(employee).getAddress());           
        }
        employeeDTO.getAddress().setId(employee.getAddress().getId());
        employee = convertToEntity(employeeDTO);

        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    public void deleteEmployee(Long id) {
    	getEmployeeById(id);
        employeeRepository.deleteById(id);
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO(employee);
        employeeDTO.setAddress(addressService.convertToDTO(employee.getAddress()));
        return employeeDTO;
    }

    public Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = objectMapper.convertValue(employeeDTO, Employee.class);
        employee.setAddress(addressService.convertToEntity(employeeDTO.getAddress()));
        return employee;
    }

    public EmployeeDTO getEmployeeByName(String name) {
        Employee employee = employeeRepository.findByFirstName(name)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with name " + name));
        return convertToDTO(employee);
    }
}
