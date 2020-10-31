package com.mycompany.hw_l31_docker.repositories;

import com.mycompany.hw_l31_docker.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
