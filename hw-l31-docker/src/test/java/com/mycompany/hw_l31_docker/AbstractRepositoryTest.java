package com.mycompany.hw_l31_docker;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractRepositoryTest {

    private static final PostgreSQLContainer<PostgresContainer> POSTGRE_SQL_CONTAINER = PostgresContainer.getInstance();

    static {
        POSTGRE_SQL_CONTAINER.start();
    }
}
