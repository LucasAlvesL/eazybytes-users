package com.eazybytes.services;

import com.eazybytes.dtos.CustomerBalanceResponseDTO;
import com.eazybytes.dtos.CustomerProfileResponseDTO;
import com.eazybytes.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CustomerBalanceService {

    private final CustomerRepository repository;

    private final RedisTemplate<String, Object> redisTemplate;

    public CustomerBalanceService(CustomerRepository repository, RedisTemplate<String, Object> redisTemplate) {
        this.repository = repository;
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public CustomerBalanceResponseDTO deposit(UUID customerId, BigDecimal amount) {
        String redisKey = "customer:profile:" + customerId;

        CustomerBalanceResponseDTO cachedProfile = (CustomerBalanceResponseDTO) redisTemplate.opsForValue().get(redisKey);
        if (cachedProfile != null) {
            return cachedProfile;
        }

        var customer = this.repository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be positive");

        customer.setBalance(customer.getBalance().add(amount));
        this.repository.save(customer);

        CustomerBalanceResponseDTO response = new CustomerBalanceResponseDTO(customer.getBalance());

        redisTemplate.opsForValue().set(redisKey, response);

        return response;
    }

    @Transactional
    public CustomerBalanceResponseDTO withdraw(UUID customerId, BigDecimal amount) {
        String redisKey = "customer:profile:" + customerId;

        CustomerBalanceResponseDTO cachedProfile = (CustomerBalanceResponseDTO) redisTemplate.opsForValue().get(redisKey);
        if (cachedProfile != null) {
            return cachedProfile;
        }

        var customer = this.repository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        if (customer.getBalance().compareTo(amount) < 0)
            throw new IllegalArgumentException("Insufficient funds");

        customer.setBalance(customer.getBalance().subtract(amount));
        this.repository.save(customer);

        CustomerBalanceResponseDTO response = new CustomerBalanceResponseDTO(customer.getBalance());

        redisTemplate.opsForValue().set(redisKey, response);

        return response;
    }
}
