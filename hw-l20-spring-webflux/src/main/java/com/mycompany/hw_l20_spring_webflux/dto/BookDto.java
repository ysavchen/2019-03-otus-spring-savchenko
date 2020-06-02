package com.mycompany.hw_l20_spring_webflux.dto;

import com.mycompany.hw_l20_spring_webflux.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BookDto {

    private String id;
    private String title;
    private AuthorDto author;
    private GenreDto genre;

    public static Book toDomainObject(BookDto dto) {
        return new Book(
                dto.id, dto.title,
                AuthorDto.toDomainObject(dto.author),
                GenreDto.toDomainObject(dto.genre)
        );
    }

    public static BookDto toDto(Book book) {
        return new BookDto(
                book.getId(), book.getTitle(),
                AuthorDto.toDto(book.getAuthor()),
                GenreDto.toDto(book.getGenre())
        );
    }
}
