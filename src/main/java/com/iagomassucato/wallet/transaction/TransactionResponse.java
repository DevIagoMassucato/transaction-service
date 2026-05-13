package com.iagomassucato.wallet.transaction;

import com.iagomassucato.wallet.user.UserEntity;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionResponse (
        BigDecimal amount,
        UserEntity sender,
        UserEntity receiver,
        OffsetDateTime timestamp
){
    public static TransactionResponse fromEntity(TransactionEntity transactionEntity){
        return new TransactionResponse(
                transactionEntity.getAmount(),
                transactionEntity.getSender(),
                transactionEntity.getReceiver(),
                transactionEntity.getTimestamp()
        );
    }
}
