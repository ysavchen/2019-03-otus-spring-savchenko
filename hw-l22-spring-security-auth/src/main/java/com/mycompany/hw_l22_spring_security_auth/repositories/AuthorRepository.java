package com.mycompany.hw_l22_spring_security_auth.repositories;

import com.mycompany.hw_l22_spring_security_auth.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
