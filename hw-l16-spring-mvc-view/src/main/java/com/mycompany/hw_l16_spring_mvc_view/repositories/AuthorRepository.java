package com.mycompany.hw_l16_spring_mvc_view.repositories;

import com.mycompany.hw_l16_spring_mvc_view.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
