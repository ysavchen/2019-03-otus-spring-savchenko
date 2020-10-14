package com.mycompany.hw_l25_spring_batch.domain.mongo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AirportMongo {

    private String name;
    private String city;

    public AirportMongo(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
