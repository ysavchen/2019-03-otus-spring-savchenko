package com.mycompany.hw_l24_spring_security_authorization.service;

import com.mycompany.hw_l24_spring_security_authorization.domain.Authority;
import com.mycompany.hw_l24_spring_security_authorization.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityDbServiceImpl implements AuthorityDbService {

    private final AuthorityRepository authorityRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Authority> findAuthoritiesByUserId(long id) {
        return authorityRepository.findAuthoritiesByUserId(id);
    }
}
