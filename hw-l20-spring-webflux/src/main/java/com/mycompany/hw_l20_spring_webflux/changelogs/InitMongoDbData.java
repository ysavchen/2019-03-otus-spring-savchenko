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

    private final Author pratt = new Author("Philip", "Pratt");
    private final Author learn = new Author("Michael", "Learn");
    private final Author clear = new Author("James", "Clear");

    private Book guideBook;
    private Book conceptsBook;
    private Book sqlCodingBook;
    private Book atomicHabits;

    @ChangeSet(order = "000", id = "dropDB", author = "ysavchen", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initBooks", author = "ysavchen", runAlways = true)
    public void initBooks(MongoTemplate template) {
        guideBook = template.save(new Book("A Guide to SQL", pratt, computers));
        conceptsBook = template.save(new Book("Concepts of Database Management", pratt, computers));
        sqlCodingBook = template.save(new Book("SQL Programming and Coding", learn, computers));
        atomicHabits = template.save(new Book("Atomic Habits", clear, psychology));
    }
}
