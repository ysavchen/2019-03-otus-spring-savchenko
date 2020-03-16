package com.mycompany.hw_l13_spring_data_nosql.test_changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.mycompany.hw_l13_spring_data_nosql.domain.Author;
import com.mycompany.hw_l13_spring_data_nosql.domain.Book;
import com.mycompany.hw_l13_spring_data_nosql.domain.Comment;
import com.mycompany.hw_l13_spring_data_nosql.domain.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class InitMongoDbDataChangeLog {

    private final Genre computers = new Genre("Computers & Technology").setId("1");

    private Author pratt;
    private Author learn;

    private Book guideBook;
    private Book conceptsBook;
    private Book sqlCodingBook;

    private Comment firstCommentGuide;
    private Comment secondCommentGuide;
    private Comment commentConcepts;

    @ChangeSet(order = "000", id = "dropDB", author = "ysavchen", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "ysavchen", runAlways = true)
    public void initAuthors(MongoTemplate template) {
        pratt = template.save(new Author("Philip", "Pratt").setId("1"));
        learn = template.save(new Author("Michael", "Learn").setId("2"));
    }

    @ChangeSet(order = "002", id = "initBooks", author = "ysavchen", runAlways = true)
    public void initBooks(MongoTemplate template) {
        guideBook = template.save(new Book("A Guide to SQL", pratt, computers).setId("1"));
        conceptsBook = template.save(new Book("Concepts of Database Management", pratt, computers).setId("2"));
        sqlCodingBook = template.save(new Book("SQL Programming and Coding", learn, computers).setId("3"));
    }

    @ChangeSet(order = "003", id = "initComments", author = "ysavchen", runAlways = true)
    public void initComments(MongoTemplate template) {
        firstCommentGuide = template.save(new Comment("First comment - Guide", guideBook).setId("1"));
        secondCommentGuide = template.save(new Comment("Second comment - Guide", guideBook).setId("2"));
        commentConcepts = template.save(new Comment("Comment - Concepts", conceptsBook).setId("3"));
    }
}
