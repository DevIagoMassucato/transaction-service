package com.iagomassucato.wallet.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record UserRequest(
        @NotBlank(message = "firstName is required")
        String firstName,

        @NotBlank(message = "lastName is required")
        String lastName,

        @NotBlank(message = "cpf is required")
        String cpf,

        @Email(message = "email is invalid")
        @NotBlank(message = "email is required")
        String email,

        @NotBlank(message = "password is required")
        String password,

        @NotNull(message = "balance is required")
        BigDecimal balance,

        @NotNull(message = "userType is required")
        UserType userType) {

}
