package com.mycompany.hw_l13_spring_data_nosql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableConfigurationProperties
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}
