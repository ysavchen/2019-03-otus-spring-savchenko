package com.mycompany.hw_l20_spring_webflux.repositories;

import com.mycompany.hw_l20_spring_webflux.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
