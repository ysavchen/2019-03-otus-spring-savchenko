package com.mycompany.hw_l29_spring_boot_actuator.repositories;

import com.mycompany.hw_l29_spring_boot_actuator.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
