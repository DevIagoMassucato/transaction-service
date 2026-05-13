package com.iagomassucato.wallet.transaction;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TransactionRequest(
        @NotNull(message = "value is required")
        BigDecimal value,

        @NotNull(message = "senderId is required")
        Long senderId,

        @NotNull(message = "receiverId is required")
        Long receiverId) {

}
