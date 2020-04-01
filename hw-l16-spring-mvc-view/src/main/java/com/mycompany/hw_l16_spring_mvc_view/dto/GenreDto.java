package com.mycompany.hw_l16_spring_mvc_view.dto;

import com.mycompany.hw_l16_spring_mvc_view.domain.Genre;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GenreDto {

    private final long id;
    private final String name;

    public static Genre toDomainObject(GenreDto dto) {
        return new Genre(dto.id, dto.name);
    }

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
