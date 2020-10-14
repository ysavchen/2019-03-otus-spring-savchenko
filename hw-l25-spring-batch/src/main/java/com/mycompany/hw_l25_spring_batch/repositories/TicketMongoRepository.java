package com.mycompany.hw_l25_spring_batch.repositories;

import com.mycompany.hw_l25_spring_batch.domain.mongo.TicketMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketMongoRepository extends MongoRepository<TicketMongo, String> {
}
