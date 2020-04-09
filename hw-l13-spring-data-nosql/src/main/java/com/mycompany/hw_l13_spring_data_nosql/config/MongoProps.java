package com.mycompany.hw_l13_spring_data_nosql.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("spring.data.mongodb")
public class MongoProps {

    private int port;
    private String database;
    private String uri;
    private String changelogs;

}
