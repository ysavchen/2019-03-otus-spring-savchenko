package com.mycompany.hw_l20_spring_webflux.repositories;

import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Mono;

public interface BookRepositoryCustom {

    Mono<UpdateResult> updateTitle(String id, String title);
}
