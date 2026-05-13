package com.iagomassucato.wallet.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserValidator {

    private final UserRepository userRepository;

    public void validateUniqueFields(String cpf, String email) {

        if (userRepository.existsByCpf(cpf)) {
            throw new IllegalStateException("user already exists with cpf: " + cpf);
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("user already exists with email: " + email);
        }
    }
}
