package com.mycompany.hw_l20_spring_webflux.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.mycompany.hw_l20_spring_webflux.domain.Author;
import com.mycompany.hw_l20_spring_webflux.domain.Book;
import com.mycompany.hw_l20_spring_webflux.domain.Genre;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

@ChangeLog(order = "001")
public class InitMongoDbData {

    private final Genre computers = new Genre("Computers & Technology");
    private final Genre psychology = new Genre("Psychology & Mental Health");

    private Mono<Author> pratt;
    private Mono<Author> learn;
    private Mono<Author> clear;

    private Mono<Book> guideBook;
    private Mono<Book> conceptsBook;
    private Mono<Book> sqlCodingBook;
    private Mono<Book> atomicHabits;

    @ChangeSet(order = "000", id = "dropDB", author = "ysavchen", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "ysavchen", runAlways = true)
    public void initAuthors(ReactiveMongoTemplate template) {
        pratt = template.save(new Author("Philip", "Pratt"));
        learn = template.save(new Author("Michael", "Learn"));
        clear = template.save(new Author("James", "Clear"));
    }

    @ChangeSet(order = "002", id = "initBooks", author = "ysavchen", runAlways = true)
    public void initBooks(ReactiveMongoTemplate template) {
        guideBook = template.save(new Book("A Guide to SQL", pratt.block(), computers));
        conceptsBook = template.save(new Book("Concepts of Database Management", pratt.block(), computers));
        sqlCodingBook = template.save(new Book("SQL Programming and Coding", learn.block(), computers));
        atomicHabits = template.save(new Book("Atomic Habits", clear.block(), psychology));
    }
}
