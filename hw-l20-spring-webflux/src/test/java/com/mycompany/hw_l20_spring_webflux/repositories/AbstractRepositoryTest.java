package com.mycompany.hw_l20_spring_webflux.repositories;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({
        "com.mycompany.hw_l20_spring_webflux.config",
        "com.mycompany.hw_l20_spring_webflux.repositories"})
public abstract class AbstractRepositoryTest {
}