package com.iagomassucato.wallet.transaction;

import com.iagomassucato.wallet.user.UserEntity;
import com.iagomassucato.wallet.user.UserType;
import org.springframework.stereotype.Component;

@Component
public class TransactionValidator {

    public void validateTransaction(UserEntity userEntity){
        if (userEntity.getUserType() == UserType.MERCHANT) {
            throw new IllegalStateException("transaction not allowed for user type: " + userEntity.getUserType());
        }
    }
}
