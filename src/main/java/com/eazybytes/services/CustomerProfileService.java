package com.eazybytes.services;

import com.eazybytes.dtos.CustomerAccountTypeRequestDTO;
import com.eazybytes.dtos.CustomerProfileResponseDTO;
import com.eazybytes.dtos.CustomerUpdateProfileRequestDTO;
import com.eazybytes.repository.CustomerRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerProfileService {

    private final CustomerRepository repository;
    private final RedisTemplate<String, Object> redisTemplate;

    public CustomerProfileService(CustomerRepository repository, RedisTemplate<String, Object> redisTemplate) {
        this.repository = repository;
        this.redisTemplate = redisTemplate;
    }

    public CustomerProfileResponseDTO execute(UUID customerId) {
        String redisKey = "customer:profile:" + customerId;

        CustomerProfileResponseDTO cachedProfile = (CustomerProfileResponseDTO) redisTemplate.opsForValue().get(redisKey);
        if (cachedProfile != null) {
            return cachedProfile;
        }

        var customer = this.repository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        CustomerProfileResponseDTO profile = CustomerProfileResponseDTO.builder()
                .firstName(customer.getFirstName())
                .middleName(customer.getMiddleName())
                .lastName(customer.getLastName())
                .balance(customer.getBalance())
                .email(customer.getEmail())
                .accountNumber(customer.getAccountNumber())
                .accountType(customer.getAccountType())
                .build();

        redisTemplate.opsForValue().set(redisKey, profile);

        return profile;
    }

    public CustomerProfileResponseDTO update(UUID customerId, CustomerUpdateProfileRequestDTO updatedProfile) {
        String redisKey = "customer:profile:" + customerId;

        CustomerProfileResponseDTO cachedProfile = (CustomerProfileResponseDTO) redisTemplate.opsForValue().get(redisKey);
        if (cachedProfile != null) {
            return cachedProfile;
        }

        var customer = this.repository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.setFirstName(updatedProfile.firstName());
        customer.setMiddleName(updatedProfile.middleName());
        customer.setLastName(updatedProfile.lastName());

        this.repository.save(customer);

        CustomerProfileResponseDTO profileResponse = CustomerProfileResponseDTO.builder()
                .firstName(customer.getFirstName())
                .middleName(customer.getMiddleName())
                .lastName(customer.getLastName())
                .balance(customer.getBalance())
                .email(customer.getEmail())
                .accountNumber(customer.getAccountNumber())
                .accountType(customer.getAccountType())
                .build();

        redisTemplate.opsForValue().set(redisKey, updatedProfile);

        return profileResponse;
    }

    public CustomerAccountTypeRequestDTO updateAccountType(UUID customerId, CustomerAccountTypeRequestDTO accountTypeRequest) {
        String redisKey = "customer:accountType:" + customerId;

        CustomerAccountTypeRequestDTO cachedAccountType = (CustomerAccountTypeRequestDTO) redisTemplate.opsForValue().get(redisKey);
        if (cachedAccountType != null) {
            return cachedAccountType;
        }

        var customer = this.repository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.setAccountType(accountTypeRequest.accountType());
        this.repository.save(customer);

        redisTemplate.opsForValue().set(redisKey, accountTypeRequest);

        return accountTypeRequest;
    }
}
