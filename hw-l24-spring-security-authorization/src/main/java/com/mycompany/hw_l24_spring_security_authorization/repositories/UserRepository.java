package com.mycompany.hw_l24_spring_security_authorization.repositories;

import com.mycompany.hw_l24_spring_security_authorization.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
