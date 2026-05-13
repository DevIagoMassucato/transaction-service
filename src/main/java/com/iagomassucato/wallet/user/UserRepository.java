package com.iagomassucato.wallet.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

}
