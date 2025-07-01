package com.eazybytes.services;

import com.eazybytes.dtos.CustomerProfileResponseDTO;
import com.eazybytes.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerProfileService {

    private final CustomerRepository repository;

    public CustomerProfileService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerProfileResponseDTO execute(UUID customerId) {
        var customer = this.repository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        return CustomerProfileResponseDTO.builder()
                .firstName(customer.getFirstName())
                .middleName(customer.getMiddleName())
                .lastName(customer.getLastName())
                .balance(customer.getBalance())
                .email(customer.getEmail())
                .accountNumber(customer.getAccountNumber())
                .accountType(customer.getAccountType())
                .build();
    }

}
