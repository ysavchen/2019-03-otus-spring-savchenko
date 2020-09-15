package com.mycompany.hw_l22_spring_security_auth.repositories;

import com.mycompany.hw_l22_spring_security_auth.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
