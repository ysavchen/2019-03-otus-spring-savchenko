package com.mycompany.hw_l20_spring_webflux.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.mycompany.hw_l20_spring_webflux.domain.Author;
import com.mycompany.hw_l20_spring_webflux.domain.Book;
import com.mycompany.hw_l20_spring_webflux.domain.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class InitMongoDbData {

    private final Genre computers = new Genre("Computers & Technology");
    private final Genre psychology = new Genre("Psychology & Mental Health");

    private Author pratt;
    private Author learn;
    private Author clear;

    private Book guideBook;
    private Book conceptsBook;
    private Book sqlCodingBook;
    private Book atomicHabits;

    @ChangeSet(order = "000", id = "dropDB", author = "ysavchen", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "ysavchen", runAlways = true)
    public void initAuthors(MongoTemplate template) {
        pratt = template.save(new Author("Philip", "Pratt"));
        learn = template.save(new Author("Michael", "Learn"));
        clear = template.save(new Author("James", "Clear"));
    }

    @ChangeSet(order = "002", id = "initBooks", author = "ysavchen", runAlways = true)
    public void initBooks(MongoTemplate template) {
        guideBook = template.save(new Book(null, "A Guide to SQL", pratt, computers));
        conceptsBook = template.save(new Book(null, "Concepts of Database Management", pratt, computers));
        sqlCodingBook = template.save(new Book(null, "SQL Programming and Coding", learn, computers));
        atomicHabits = template.save(new Book(null, "Atomic Habits", clear, psychology));
    }
}
