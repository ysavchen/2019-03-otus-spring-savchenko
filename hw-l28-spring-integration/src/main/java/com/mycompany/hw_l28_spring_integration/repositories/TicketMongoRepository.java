package com.mycompany.hw_l28_spring_integration.repositories;

import com.mycompany.hw_l28_spring_integration.domain.mongo.TicketMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketMongoRepository extends MongoRepository<TicketMongo, String> {
}
