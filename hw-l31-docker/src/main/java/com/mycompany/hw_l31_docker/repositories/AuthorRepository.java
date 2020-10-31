package com.mycompany.hw_l31_docker.repositories;

import com.mycompany.hw_l31_docker.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
