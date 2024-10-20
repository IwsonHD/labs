package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

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

    @Bean
    public CorsWebFilter corsWebFilter(){
        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET","POST","DELETE"));
        corsConfig.addAllowedHeader("*");
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);

    }

}