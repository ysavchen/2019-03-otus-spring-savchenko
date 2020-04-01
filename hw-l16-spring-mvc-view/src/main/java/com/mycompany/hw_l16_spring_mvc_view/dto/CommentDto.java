package com.mycompany.hw_l16_spring_mvc_view.dto;

import com.mycompany.hw_l16_spring_mvc_view.domain.Comment;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommentDto {

    private final long id;
    private final String content;
    private final BookDto bookDto;

    public static Comment toDomainObject(CommentDto dto) {
        return new Comment(dto.id, dto.content, BookDto.toDomainObject(dto.bookDto));
    }

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContent(), BookDto.toDto(comment.getBook()));
    }
}
