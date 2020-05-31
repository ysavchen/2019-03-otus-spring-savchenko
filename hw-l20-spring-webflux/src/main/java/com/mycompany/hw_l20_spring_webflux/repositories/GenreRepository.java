package com.mycompany.hw_l20_spring_webflux.repositories;

import com.mycompany.hw_l20_spring_webflux.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
