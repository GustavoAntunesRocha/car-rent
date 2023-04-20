package br.com.antunes.gustavo.carrentproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.antunes.gustavo.carrentproject.model.dto.EmployeeDTO;
import br.com.antunes.gustavo.carrentproject.service.EmployeeService;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false) Long id, @RequestParam(required = false) String name) {
        if (id != null) {
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        } else if (name != null) {
            return ResponseEntity.ok(employeeService.getEmployeeByName(name));
        } else {
            return ResponseEntity.ok(employeeService.getAllEmployees());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.created(null).body(employeeService.createEmployee(employeeDTO));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeDTO));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

}
