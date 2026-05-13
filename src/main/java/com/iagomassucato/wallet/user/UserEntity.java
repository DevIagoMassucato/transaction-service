package com.iagomassucato.wallet.user;

import com.iagomassucato.wallet.shared.AbstractEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_cpf", columnNames = "cpf"),
                @UniqueConstraint(name = "uk_users_email", columnNames = "email")
        }
)
@NoArgsConstructor
@Getter
public class UserEntity extends AbstractEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder
    public UserEntity(
            String firstName,
            String lastName,
            String cpf,
            String email ,
            String password,
            BigDecimal balance,
            UserType userType
    ){
        this.firstName = validateString(firstName, "firstName");
        this.lastName = validateString(lastName, "lastName");
        this.cpf = validateString(cpf, "cpf");
        this.email = validateString(email, "email");
        this.password = validateString(password, "password");
        this.balance = validateBigDecimal(balance, "balance");
        this.userType = validateUserType(userType);
    }

    public void credit(BigDecimal amount) {
        BigDecimal value = validateBigDecimal(amount, "amount");
        this.balance = this.balance.add(value);
    }

    public void debit(BigDecimal amount) {
        BigDecimal value = validateBigDecimal(amount, "amount");
        validateSufficientBalance(value);
        this.balance = this.balance.subtract(value);
    }


    private void validateSufficientBalance(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("insufficient funds");
        }
    }

    private String validateString(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return value;
    }

    private BigDecimal validateBigDecimal(BigDecimal balance, String fieldName) {
        if (balance == null || balance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return balance;
    }

    private UserType validateUserType(UserType userType){
        if (userType == null){
            throw new IllegalArgumentException("userType is required");
        }
        return userType;
    }

}

