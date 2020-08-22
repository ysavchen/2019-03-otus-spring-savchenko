package com.mycompany.hw_l20_spring_webflux.repositories;

import reactor.core.publisher.Mono;

public interface BookRepositoryCustom {

    Mono<Void> updateTitle(String id, String title);
}
