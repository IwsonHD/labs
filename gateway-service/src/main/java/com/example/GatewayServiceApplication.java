package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            @Value("${labs.gateway.host}") String host,
            @Value("${labs.animalservice.url}") String animalUrl,
            @Value("${labs.specieservice.url}") String specieUrl
    ) {
        return builder.routes()
                .route("animals", route -> route
                        .host(host)
                        .and()
                        .path("/api/animals", "/api/animals/{id}", "/api/animals/specie/{specieId}")
                        .and()
                        .method("GET", "POST", "DELETE") // Obsługujemy DELETE
                        .uri(animalUrl))
                .route("species", route -> route
                        .host(host)
                        .and()
                        .path("/api/species", "/api/species/{id}")
                        .and()
                        .method("GET", "POST", "DELETE") // Obsługujemy DELETE
                        .uri(specieUrl))
                .build();
    }
}