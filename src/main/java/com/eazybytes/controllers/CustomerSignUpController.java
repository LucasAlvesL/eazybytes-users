package com.eazybytes.controllers;

import com.eazybytes.dtos.CustomerSignUpRequestDTO;
import com.eazybytes.services.CustomerSignUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
@Tag(name = "Customer Sign Up", description = "Customer sign up")
public class CustomerSignUpController {

    @Autowired
    private CustomerSignUpService service;

    @PostMapping("/signup")
    @Operation(summary = "Customer registration",
            description = "This endpoint allows a new customer to sign up by providing their details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = CustomerSignUpRequestDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User already exists or invalid input")
    })
    public ResponseEntity<Object> signup(@Valid @RequestBody CustomerSignUpRequestDTO request) {
        try {
            var result = this.service.execute(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
