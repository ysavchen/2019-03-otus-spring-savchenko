package com.mycompany.hw_l25_spring_batch.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.mycompany.hw_l25_spring_batch.domain.Author;
import com.mycompany.hw_l25_spring_batch.domain.Book;
import com.mycompany.hw_l25_spring_batch.domain.Comment;
import com.mycompany.hw_l25_spring_batch.domain.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class InitMongoDbDataChangeLog {

    private final Genre computers = new Genre("Computers & Technology");
    private final Genre psychology = new Genre("Psychology & Mental Health");

    private Author pratt;
    private Author learn;
    private Author clear;

    private Book guideBook;
    private Book conceptsBook;
    private Book sqlCodingBook;
    private Book atomicHabits;

    private Comment firstCommentGuide;
    private Comment secondCommentGuide;
    private Comment commentConcepts;

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
        guideBook = template.save(new Book("A Guide to SQL", pratt, computers));
        conceptsBook = template.save(new Book("Concepts of Database Management", pratt, computers));
        sqlCodingBook = template.save(new Book("SQL Programming and Coding", learn, computers));
        atomicHabits = template.save(new Book("Atomic Habits", clear, psychology));
    }

    @ChangeSet(order = "003", id = "initComments", author = "ysavchen", runAlways = true)
    public void initComments(MongoTemplate template) {
        firstCommentGuide = template.save(new Comment("First comment - Guide", guideBook));
        secondCommentGuide = template.save(new Comment("Second comment - Guide", guideBook));
        commentConcepts = template.save(new Comment("Comment - Concepts", conceptsBook));
    }
}
