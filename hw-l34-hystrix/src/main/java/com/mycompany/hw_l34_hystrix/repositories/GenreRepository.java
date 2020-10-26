package com.mycompany.hw_l34_hystrix.repositories;

import com.mycompany.hw_l34_hystrix.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
