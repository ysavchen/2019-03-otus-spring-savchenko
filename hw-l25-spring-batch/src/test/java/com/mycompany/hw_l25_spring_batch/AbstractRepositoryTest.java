package com.mycompany.hw_l25_spring_batch;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({
        "com.mycompany.hw_l13_spring_data_nosql.config",
        "com.mycompany.hw_l13_spring_data_nosql.repositories"})
public abstract class AbstractRepositoryTest {
}
