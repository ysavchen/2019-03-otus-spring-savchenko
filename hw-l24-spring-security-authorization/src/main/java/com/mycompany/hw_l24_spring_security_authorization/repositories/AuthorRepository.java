package com.mycompany.hw_l24_spring_security_authorization.repositories;

import com.mycompany.hw_l24_spring_security_authorization.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
