package com.iagomassucato.wallet.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("user not found with id: " + id));
    }
}
