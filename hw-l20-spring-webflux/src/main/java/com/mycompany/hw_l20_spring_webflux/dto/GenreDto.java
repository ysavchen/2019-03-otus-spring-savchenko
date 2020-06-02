package com.mycompany.hw_l20_spring_webflux.dto;

import com.mycompany.hw_l20_spring_webflux.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GenreDto {

    private String id;
    private String name;

    public static Genre toDomainObject(GenreDto dto) {
        return new Genre(dto.id, dto.name);
    }

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
