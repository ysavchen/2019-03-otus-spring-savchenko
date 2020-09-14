package com.mycompany.hw_l24_spring_security_authorization.service;

import com.mycompany.hw_l24_spring_security_authorization.domain.Authority;

import java.util.List;

public interface AuthorityDbService {

    List<Authority> findAuthoritiesByUserId(long id);
}
