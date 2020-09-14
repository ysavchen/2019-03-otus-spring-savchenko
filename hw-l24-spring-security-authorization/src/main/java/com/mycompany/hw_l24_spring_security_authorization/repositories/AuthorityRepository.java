package com.mycompany.hw_l24_spring_security_authorization.repositories;

import com.mycompany.hw_l24_spring_security_authorization.domain.Authority;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @EntityGraph("authority-entity-graph")
    List<Authority> findAuthoritiesByUserId(long id);
}
