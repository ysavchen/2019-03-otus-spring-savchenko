package com.mycompany.hw_l28_spring_integration.repositories;

import com.mycompany.hw_l28_spring_integration.domain.mongo.FlightMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface FlightMongoRepository extends MongoRepository<FlightMongo, String> {

    Optional<FlightMongo> findByFlightNo(UUID id);
}
