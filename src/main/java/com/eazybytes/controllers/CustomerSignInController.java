package com.eazybytes.controllers;

import com.eazybytes.dtos.CustomerSignInRequestDTO;
import com.eazybytes.services.CustomerSignInService;
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
@RequestMapping("/customers")
@Tag(name = "Customer Sign In", description = "Customer sign in")
public class CustomerSignInController {

    @Autowired
    private CustomerSignInService service;

    @PostMapping("/signin")
    @Operation(summary = "Customer Sign In",
            description = "This endpoint allows an existing customer to sign in by providing their email and password.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CustomerSignInRequestDTO.class))
            }),
            @ApiResponse(responseCode = "401", description = "Access denied due to invalid credentials")
    })
    public ResponseEntity<Object> signin(@Valid @RequestBody CustomerSignInRequestDTO request) {
        try {
            var token = this.service.execute(request);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
