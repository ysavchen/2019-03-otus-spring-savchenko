package com.mycompany.hw_l20_spring_webflux.test_changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.mycompany.hw_l20_spring_webflux.domain.Author;
import com.mycompany.hw_l20_spring_webflux.domain.Book;
import com.mycompany.hw_l20_spring_webflux.domain.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class InitMongoDbDataChangeLog {

    private final Genre computers = new Genre("test-genre-1", "Computers & Technology");

    private final Author pratt = new Author("test-author-1", "Philip", "Pratt");
    private final Author learn = new Author("test-author-2", "Michael", "Learn");

    private Book guideBook;
    private Book conceptsBook;
    private Book sqlCodingBook;

    @ChangeSet(order = "000", id = "dropDB", author = "ysavchen", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initBooks", author = "ysavchen", runAlways = true)
    public void initBooks(MongoTemplate template) {
        guideBook = template.save(new Book("test-book-1", "A Guide to SQL", pratt, computers));
        conceptsBook = template.save(new Book("test-book-2", "Concepts of Database Management", pratt, computers));
        sqlCodingBook = template.save(new Book("test-book-3", "SQL Programming and Coding", learn, computers));
    }
}
