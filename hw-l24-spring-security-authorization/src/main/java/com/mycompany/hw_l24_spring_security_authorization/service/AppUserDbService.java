package com.mycompany.hw_l24_spring_security_authorization.service;

import com.mycompany.hw_l24_spring_security_authorization.domain.AppUser;

import java.util.Optional;

public interface AppUserDbService {

    Optional<AppUser> findByEmail(String email);

}
