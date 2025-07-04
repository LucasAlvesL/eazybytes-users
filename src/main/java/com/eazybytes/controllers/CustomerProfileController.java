package com.eazybytes.controllers;

import com.eazybytes.dtos.CustomerAccountTypeRequestDTO;
import com.eazybytes.dtos.CustomerProfileResponseDTO;
import com.eazybytes.dtos.CustomerUpdateProfileRequestDTO;
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
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/profile/update")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "Update Customer Profile", description = "Updates the profile details of the authenticated customer.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CustomerUpdateProfileRequestDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not Found"),
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> updateCustomerProfile(HttpServletRequest request, CustomerUpdateProfileRequestDTO updatedProfile) {
        var customerId = request.getAttribute("customer_id");

        try {
            var profile = service.update(UUID.fromString(customerId.toString()), updatedProfile);
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/profile/update/account-type")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "Update Customer Account Type", description = "Updates the account type of the authenticated customer.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CustomerAccountTypeRequestDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "User not Found"),
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> updateCustomerAccountType(HttpServletRequest request, @RequestBody CustomerAccountTypeRequestDTO accountTypeRequest) {
        var customerId = request.getAttribute("customer_id");

        try {
            var profile = service.updateAccountType(UUID.fromString(customerId.toString()), accountTypeRequest);
            return ResponseEntity.ok().body(profile);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
