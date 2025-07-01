package com.eazybytes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CustomerSignInRequestDTO(
        @Schema(description = "Email address of the customer", example = "johndoe@email.com")
        @NotBlank
        String email,
        @NotBlank
        @Schema(description = "Password for the customer account", example = "securePassword123")
        String password
) {
}
