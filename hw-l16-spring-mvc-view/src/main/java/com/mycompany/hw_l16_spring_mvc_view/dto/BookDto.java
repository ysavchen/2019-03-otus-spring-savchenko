package com.mycompany.hw_l16_spring_mvc_view.dto;

import com.mycompany.hw_l16_spring_mvc_view.domain.Book;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BookDto {

    private final long id;
    private final String title;
    private final AuthorDto author;
    private final GenreDto genre;

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
