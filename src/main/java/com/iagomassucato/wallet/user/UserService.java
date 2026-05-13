package com.iagomassucato.wallet.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDomainService userDomainService;
    private final UserValidator userValidator;

    public UserResponse create(UserRequest userRequest){
        userValidator.validateUniqueFields(userRequest.cpf(), userRequest.email());
        UserEntity userEntity = toEntity(userRequest);
        UserEntity userEntitySaved = userDomainService.save(userEntity);
        return UserResponse.fromEntity(userEntitySaved);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::fromEntity)
                .toList();
    }

    public UserResponse findById(long id) {
        UserEntity userEntity = userDomainService.findById(id);
        return UserResponse.fromEntity(userEntity);

    }

    private UserEntity toEntity(UserRequest userRequest){
        return UserEntity.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .cpf(userRequest.cpf())
                .email(userRequest.email())
                .password(userRequest.password())
                .balance(userRequest.balance())
                .userType(userRequest.userType())
                .build();
    }

}
