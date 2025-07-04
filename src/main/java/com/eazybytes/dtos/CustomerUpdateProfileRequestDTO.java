package com.eazybytes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CustomerUpdateProfileRequestDTO(

        @Schema(description = "First name of the customer", example = "John")
        @NotBlank String firstName,

        @Schema(description = "Middle name of the customer", example = "A.")
        String middleName,

        @Schema(description = "Last name of the customer", example = "Doe")
        @NotBlank String lastName
) {
}
