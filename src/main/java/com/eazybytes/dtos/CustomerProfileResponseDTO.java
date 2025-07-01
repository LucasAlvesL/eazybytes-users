package com.eazybytes.dtos;

import com.eazybytes.entity.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileResponseDTO {

    @Schema(example = "John")
    private String firstName;
    @Schema(example = "A.")
    private String middleName;
    @Schema(example = "Doe")
    private String lastName;
    @Schema(example = "1000.00")
    private BigDecimal balance;
    @Schema(example = "johndoe@email.com")
    private String email;
    @Schema(example = "83953529-1")
    private String accountNumber;
    @Schema(example = "SAVINGS")
    private AccountType accountType;
}
