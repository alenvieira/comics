package com.alenvieira.comics.repository;

import com.alenvieira.comics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByCpf(String cpf);

}
