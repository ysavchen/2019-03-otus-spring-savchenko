package com.mycompany.hw_l13_spring_data_nosql.repositories;

import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;
    private final AuthorRepository authorRepository;

    @Override
    public Book saveWithAuthor(Book book) {
        if (book.getAuthor() != null) {
            authorRepository.save(book.getAuthor());
        }
        return mongoTemplate.save(book);
    }
}
