package com.eazybytes.services;

import com.eazybytes.dtos.CustomerProfileResponseDTO;
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

}
