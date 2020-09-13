package com.mycompany.hw_l22_spring_security_auth.service;

import com.mycompany.hw_l22_spring_security_auth.domain.AppUser;

import java.util.Optional;

public interface AppUserDbService {

    Optional<AppUser> findByEmail(String email);

}
