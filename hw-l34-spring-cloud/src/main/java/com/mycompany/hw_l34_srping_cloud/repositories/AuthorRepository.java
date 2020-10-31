package com.mycompany.hw_l34_srping_cloud.repositories;

import com.mycompany.hw_l34_srping_cloud.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
