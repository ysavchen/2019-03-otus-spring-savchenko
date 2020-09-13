package com.mycompany.hw_l24_spring_security_authorization.repositories;

import com.mycompany.hw_l24_spring_security_authorization.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

}
