package com.rohansharma.money_transfer_rest_java;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
class TestContainersConfiguration {

    @Bean
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer("postgres:16")
                .withDatabaseName("testdb")
                .withUsername("test")
                .withPassword("test");
    }
}

