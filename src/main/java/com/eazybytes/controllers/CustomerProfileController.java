package com.eazybytes.controllers;

import com.eazybytes.dtos.CustomerProfileResponseDTO;
import com.eazybytes.services.CustomerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer Profile", description = "Customer profile management")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService service;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "Customer Profile", description = "Fetches the profile details of the authenticated customer.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CustomerProfileResponseDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not Found"),
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> getCustomerProfile(HttpServletRequest request) {
        var customerId = request.getAttribute("customer_id");

        try {
            var profile = service.execute(UUID.fromString(customerId.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
