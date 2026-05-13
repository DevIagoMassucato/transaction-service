package com.iagomassucato.wallet.transaction;

import com.iagomassucato.wallet.shared.AbstractEntity;
import com.iagomassucato.wallet.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Getter
public class TransactionEntity extends AbstractEntity {

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private UserEntity receiver;

    private OffsetDateTime timestamp;

    public TransactionEntity(BigDecimal amount, UserEntity sender, UserEntity receiver) {
        this.amount = Objects.requireNonNull(amount);
        this.sender = Objects.requireNonNull(sender);
        this.receiver = Objects.requireNonNull(receiver);
        this.timestamp = OffsetDateTime.now();
    }

}
