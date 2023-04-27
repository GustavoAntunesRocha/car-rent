package br.com.antunes.gustavo.carrentproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.antunes.gustavo.carrentproject.exception.RestExceptionHandler;
import br.com.antunes.gustavo.carrentproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

        private final UserService userService;

        public UserController(UserService userService) {
                super();
                this.userService = userService;
        }

        @Operation(description = "Endpoint for user authentication and to obtain an access token", responses = {
                        @ApiResponse(responseCode = "200", description = "Successfully authenticated and received an access token", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Invalid login request or missing request parameters", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestExceptionHandler.class))),
                        @ApiResponse(responseCode = "401", description = "Unauthorized access or invalid credentials", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestExceptionHandler.class)))
        })
        @PostMapping("/login")
        public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
                return ResponseEntity.ok(userService.authenticate(request));
        }

}
