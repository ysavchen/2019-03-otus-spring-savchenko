package com.mycompany.hw_l16_spring_mvc_view.dto;

import com.mycompany.hw_l16_spring_mvc_view.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CommentDto {

    private long id;
    private String content;
    private BookDto bookDto;

    public static Comment toDomainObject(CommentDto dto) {
        return new Comment(dto.id, dto.content, BookDto.toDomainObject(dto.bookDto));
    }

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContent(), BookDto.toDto(comment.getBook()));
    }
}
