package com.iagomassucato.wallet.user;

import java.math.BigDecimal;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String cpf,
        String email,
        String password,
        BigDecimal balance,
        UserType userType){

    public static UserResponse fromEntity(UserEntity userEntity){
        return new UserResponse(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getCpf(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getBalance(),
                userEntity.getUserType()
        );
    }
}
