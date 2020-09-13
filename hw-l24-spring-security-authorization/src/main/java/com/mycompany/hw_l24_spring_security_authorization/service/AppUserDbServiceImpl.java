package com.mycompany.hw_l24_spring_security_authorization.service;

import com.mycompany.hw_l24_spring_security_authorization.domain.AppUser;
import com.mycompany.hw_l24_spring_security_authorization.repositories.AppUserRepository;
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
