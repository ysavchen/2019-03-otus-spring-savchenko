package com.mycompany.hw_l17_spring_mvc_modern_apps.repositories;

import com.mycompany.hw_l17_spring_mvc_modern_apps.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
