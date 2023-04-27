package br.com.antunes.gustavo.carrentproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.antunes.gustavo.carrentproject.controller.LoginRequest;
import br.com.antunes.gustavo.carrentproject.controller.LoginResponse;
import br.com.antunes.gustavo.carrentproject.exception.CustomException;
import br.com.antunes.gustavo.carrentproject.model.Customer;
import br.com.antunes.gustavo.carrentproject.model.Employee;
import br.com.antunes.gustavo.carrentproject.model.dto.UserDTO;
import br.com.antunes.gustavo.carrentproject.model.repository.CustomerRepository;
import br.com.antunes.gustavo.carrentproject.model.repository.EmployeeRepository;
import br.com.antunes.gustavo.carrentproject.model.repository.UserRepository;
import br.com.antunes.gustavo.carrentproject.security.JwtService;
import br.com.antunes.gustavo.carrentproject.security.Role;
import br.com.antunes.gustavo.carrentproject.security.UserEntity;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, ObjectMapper objectMapper,
            BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        super();
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    public LoginResponse authenticate(LoginRequest request) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with email " + request.getEmail()));
            var jwt = jwtService.generateToken(user);
            return new LoginResponse(jwt);
        } catch (AuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public UserDTO createUser(UserDTO userDTO, String password) throws CustomException {
        UserEntity user = modelMapper.map(userDTO, UserEntity.class);
        user.setPassword(passwordEncoder.encode(password));
        Optional<Customer> customer = customerRepository.findById(user.getPersonId());
        List<Role> roles = new ArrayList<>();
        if(customer.isPresent()) {
            roles.add(Role.CLIENT);
        	user.setRole(roles);
        } else {
        	Optional<Employee> employee = employeeRepository.findById(user.getPersonId());
        	if(employee.isPresent()) {
                roles.add(Role.EMPLOYEE);
        		user.setRole(roles);
        	} else {
        		throw new CustomException("Person not found with ID " + user.getPersonId());
        	}
        }
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new CustomException("User already exists with email " + user.getEmail());
        });
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public UserDTO getUserById(int id) throws EntityNotFoundException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID " + id));
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO getUserByEmail(String email) throws EntityNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email " + email));
        return modelMapper.map(user, UserDTO.class);
    }

    public UserEntity findUserByEmail(String email) throws EntityNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email " + email));
    }

    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(UserEntity user) throws EntityNotFoundException {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID " + user.getId()));
        UserEntity savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public void deleteUserById(int id) throws EntityNotFoundException {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID " + id));
        ;
        userRepository.delete(user);
    }

    public String getUserDTOAsJson(int id) throws EntityNotFoundException, JsonProcessingException {
        UserDTO userDTO = getUserById(id);
        return objectMapper.writeValueAsString(userDTO);
    }

    public String getAllUsersAsJson() throws JsonProcessingException {
        List<UserDTO> userDTOs = getAllUsers();
        return objectMapper.writeValueAsString(userDTOs);
    }
}
