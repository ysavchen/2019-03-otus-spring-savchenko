package com.mycompany.hw_l22_spring_security_auth.repositories;

import com.mycompany.hw_l22_spring_security_auth.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

}
