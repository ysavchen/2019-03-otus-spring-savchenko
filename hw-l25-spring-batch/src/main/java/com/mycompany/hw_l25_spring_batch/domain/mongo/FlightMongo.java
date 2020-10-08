package com.mycompany.hw_l25_spring_batch.domain.mongo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "flights")
@Accessors(chain = true)
public class FlightMongo {

    @Id
    private long id;
    private UUID flightNo = UUID.randomUUID();
    private AirportMongo departureAirport;
    private AirportMongo arrivalAirport;

}
