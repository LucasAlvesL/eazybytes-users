package com.eazybytes.dtos;

import com.eazybytes.entity.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerSignUpRequestDTO(

    @Schema(description = "First name of the customer", example = "John")
    @NotBlank String firstName,

    @Schema(description = "Middle name of the customer", example = "A.")
    String middleName,

    @Schema(description = "Last name of the customer", example = "Doe")
    @NotBlank String lastName,

    @Schema(description = "Email address of the customer", example = "johndoe@email.com")
    @Email String email,

    @Schema(description = "Password for the customer account", example = "securePassword123")
    @NotBlank String password,

    @Schema(description = "Type of account for the customer", example = "SAVINGS")
    AccountType accountType
) {}
