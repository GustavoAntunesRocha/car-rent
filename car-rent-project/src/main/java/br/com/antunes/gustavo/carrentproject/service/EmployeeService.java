package br.com.antunes.gustavo.carrentproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.antunes.gustavo.carrentproject.model.Employee;
import br.com.antunes.gustavo.carrentproject.model.dto.EmployeeDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	private final ObjectMapper objectMapper;
	
	private final AddressService addressService;
	
	public EmployeeService(EmployeeRepository employeeRepository, ObjectMapper objectMapper, AddressService addressService) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
        this.addressService = addressService;
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

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(employeeDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + employeeDTO.getId()));

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
    	return objectMapper.convertValue(employeeDTO, Employee.class);
    }

    public EmployeeDTO getEmployeeByName(String name) {
        Employee employee = employeeRepository.findByFirstName(name)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with name " + name));
        return convertToDTO(employee);
    }
}
