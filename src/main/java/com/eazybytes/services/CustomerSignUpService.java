package com.eazybytes.services;

import com.eazybytes.dtos.CustomerSignUpRequestDTO;
import com.eazybytes.entity.CustomerEntity;
import com.eazybytes.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CustomerSignUpService {

    private final CustomerRepository repository;

    private final PasswordEncoder passwordEncoder;

    public CustomerSignUpService(CustomerRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerEntity execute(CustomerSignUpRequestDTO customerSignUpRequestDTO) {
        CustomerEntity customerEntity = this.convertToEntity(customerSignUpRequestDTO);

        this.repository.findByEmail(customerEntity.getEmail())
                .ifPresent(existingCustomer -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        return this.repository.save(customerEntity);
    }

    private CustomerEntity convertToEntity(CustomerSignUpRequestDTO request) {
        CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(request, customerEntity);

        String accountNumber = generateUniqueAccountNumber();
        customerEntity.setAccountNumber(accountNumber);

        var password = passwordEncoder.encode(customerEntity.getPassword());
        customerEntity.setPassword(password);

        return customerEntity;
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;

        do {
            accountNumber = generateFormattedAccountNumber();
        } while (repository.findByAccountNumber(accountNumber).isPresent());

        return accountNumber;
    }

    private String generateFormattedAccountNumber() {
        Random random = new Random();

        int baseNumber = 10000000 + random.nextInt(90000000);
        int checkDigit = calculateLuhnCheckDigit(String.valueOf(baseNumber));

        return baseNumber + "-" + checkDigit;
    }

    private int calculateLuhnCheckDigit(String number) {
        int sum = 0;
        boolean doubleDigit = true;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        int mod = sum % 10;
        return (mod == 0) ? 0 : 10 - mod;
    }
}
