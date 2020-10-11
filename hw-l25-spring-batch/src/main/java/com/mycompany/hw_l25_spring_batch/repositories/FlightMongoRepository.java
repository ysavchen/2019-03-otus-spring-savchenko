package com.mycompany.hw_l25_spring_batch.repositories;

import com.mycompany.hw_l25_spring_batch.domain.mongo.FlightMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface FlightMongoRepository extends MongoRepository<FlightMongo, String> {

    Optional<FlightMongo> findByFlightNo(UUID id);
}
