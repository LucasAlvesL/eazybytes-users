package com.eazybytes.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eazybytes.dtos.CustomerAuthResponseDTO;
import com.eazybytes.dtos.CustomerSignInRequestDTO;
import com.eazybytes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class CustomerSignInService {

    @Value("${security.token.secret.customer}")
    private String secretKey;

    private final CustomerRepository repository;

    private final PasswordEncoder passwordEncoder;

    public CustomerSignInService(CustomerRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerAuthResponseDTO execute(CustomerSignInRequestDTO request) throws AuthenticationException {
        var customer = this.repository.findByEmail(request.email()).orElseThrow(() -> new AuthenticationException("Invalid email or password"));

        var passwordMatches = this.passwordEncoder.matches(request.password(), customer.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException("Invalid password");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("eazybytes")
                .withSubject(customer.getCustomerId().toString())
                .withClaim("roles", List.of("CUSTOMER"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return CustomerAuthResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();
    }
}
