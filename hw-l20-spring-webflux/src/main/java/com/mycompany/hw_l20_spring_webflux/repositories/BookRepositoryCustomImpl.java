package com.mycompany.hw_l20_spring_webflux.repositories;

import com.mycompany.hw_l20_spring_webflux.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final ReactiveMongoTemplate template;

    @Override
    public Mono<Void> updateTitle(String id, String title) {
        var query = new Query(where("id").is(id));
        var update = new Update().set("title", title);
        return template.updateFirst(query, update, Book.class).then();
    }
}
