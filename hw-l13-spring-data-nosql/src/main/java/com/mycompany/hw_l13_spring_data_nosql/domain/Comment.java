package com.mycompany.hw_l13_spring_data_nosql.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comments")
@Accessors(chain = true)
public class Comment {

    @Id
    private Long id;
    private String content;

    @DBRef
    private Book book;

    public Comment() {
    }

    public Comment(String content) {
        this.content = content;
    }

    public Comment(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Comment(long id, String content, Book book) {
        this.id = id;
        this.content = content;
        this.book = book;
    }
}
