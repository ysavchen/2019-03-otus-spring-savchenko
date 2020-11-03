package com.mycompany.hw_l34_srping_cloud.repositories;

import com.mycompany.hw_l34_srping_cloud.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
