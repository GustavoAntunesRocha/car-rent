package br.com.antunes.gustavo.carrentproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.antunes.gustavo.carrentproject.model.dto.CustomerDTO;
import br.com.antunes.gustavo.carrentproject.service.CustomerService;

@RestController
@RequestMapping(value = "/api/v1/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam (required = false) Long id, @RequestParam (required = false) String name) {
        if (id != null) {
            return ResponseEntity.ok(customerService.findById(id));
        } else if (name != null) {
            return ResponseEntity.ok(customerService.findByName(name));
        } else {
            return ResponseEntity.ok(customerService.findAll());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody createCustomerRequest customCustomer) {
        CustomerDTO customerDTO = customCustomer.customerDTO();
        return ResponseEntity.ok(customerService.create(customerDTO, customCustomer.password()));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.update(customerDTO));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }

    record createCustomerRequest(CustomerDTO customerDTO, String password) {}
    
}
