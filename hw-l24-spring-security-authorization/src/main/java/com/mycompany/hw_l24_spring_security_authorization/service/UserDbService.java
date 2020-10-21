package com.mycompany.hw_l24_spring_security_authorization.service;

import com.mycompany.hw_l24_spring_security_authorization.domain.User;

import java.util.Optional;

public interface UserDbService {

    Optional<User> findByEmail(String email);

}
