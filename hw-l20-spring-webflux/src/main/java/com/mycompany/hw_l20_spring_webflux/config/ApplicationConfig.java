package com.mycompany.hw_l20_spring_webflux.config;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public Mongock mongock(MongoProps mongoProps, MongoClient mongoClient) {
        return new SpringMongockBuilder(mongoClient, mongoProps.getDatabase(), mongoProps.getChangelogs())
                .build();
    }
}
