package com.eazybytes.controllers;

import com.eazybytes.dtos.CustomerBalanceRequestDTO;
import com.eazybytes.services.CustomerBalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/customers/balance")
public class CustomerBalanceController {

    @Autowired
    private CustomerBalanceService service;

    @PostMapping("/deposit")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "Deposit Money", description = "Deposits money into the customer's account.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deposit successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request or insufficient funds")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> deposit(HttpServletRequest request, @Valid @RequestBody CustomerBalanceRequestDTO customerBalanceRequestDTO) {
        var customerId = request.getAttribute("customer_id");
        try {
            var response = service.deposit(UUID.fromString(customerId.toString()), customerBalanceRequestDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/withdraw")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "Withdraw Money", description = "Withdraws money from the customer's account.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Withdrawal successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request or insufficient funds")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> withdraw(HttpServletRequest request, @Valid @RequestBody CustomerBalanceRequestDTO customerBalanceRequestDTO) {
        var customerId = request.getAttribute("customer_id");
        try {
            var response = service.withdraw(UUID.fromString(customerId.toString()), customerBalanceRequestDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
