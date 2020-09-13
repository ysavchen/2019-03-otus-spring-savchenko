package com.mycompany.hw_l22_spring_security_auth.service;

import com.mycompany.hw_l22_spring_security_auth.domain.AppUser;
import com.mycompany.hw_l22_spring_security_auth.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserDbServiceImpl implements AppUserDbService {

    private final AppUserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
