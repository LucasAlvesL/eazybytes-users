package com.eazybytes.dtos;

import com.eazybytes.entity.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;

public record CustomerAccountTypeRequestDTO(
        @Schema(description = "Account type", example = "SAVINGS")
        AccountType accountType
) {
}
