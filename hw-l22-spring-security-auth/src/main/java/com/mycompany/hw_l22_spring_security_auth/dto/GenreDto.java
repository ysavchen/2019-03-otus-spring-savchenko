package com.mycompany.hw_l22_spring_security_auth.dto;

import com.mycompany.hw_l22_spring_security_auth.domain.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GenreDto {

    private long id;
    private String name;

    public static Genre toDomainObject(GenreDto dto) {
        return new Genre(dto.id, dto.name);
    }

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }
}
