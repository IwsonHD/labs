package com.example.BusinessLogic.Utilities;

import com.example.BusinessLogic.DTO.SimplifiedSpecieDTO;
import com.example.BusinessLogic.Enums.Continents;
import com.example.BusinessLogic.Enums.DietType;
import com.example.BusinessLogic.Models.Specie;
import com.example.BusinessLogic.Services.SpecieService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class DataInitializer {

    private final SpecieService specieService;
    private final RestTemplate restTemplate;

    public DataInitializer(SpecieService specieService, RestTemplateBuilder restTemplateBuilder) {
        this.specieService = specieService;
        this.restTemplate = restTemplateBuilder.build();
    }

    @PostConstruct
    public void initializeData() {
        if (specieService.findAll().isEmpty()) {
            Specie lionSpecie = Specie.builder()
                    .id(UUID.randomUUID())
                    .name("Lion")
                    .occurring(Continents.AFRICA)
                    .dietType(DietType.CARNIVORE)
                    .build();

            Specie elephantSpecie = Specie.builder()
                    .id(UUID.randomUUID())
                    .name("Elephant")
                    .occurring(Continents.AFRICA)
                    .dietType(DietType.HERBIVORE)
                    .build();

            Specie zebraSpecie = Specie.builder()
                    .id(UUID.randomUUID())
                    .name("Zebra")
                    .occurring(Continents.AFRICA)
                    .dietType(DietType.HERBIVORE)
                    .build();

            specieService.save(lionSpecie);
            specieService.save(elephantSpecie);
            specieService.save(zebraSpecie);

            sendSpecieToAnimalService(lionSpecie);
            sendSpecieToAnimalService(elephantSpecie);
            sendSpecieToAnimalService(zebraSpecie);
        }
    }

    private void sendSpecieToAnimalService(Specie specie) {
        SimplifiedSpecieDTO specieDTO = SimplifiedSpecieDTO.builder()
                .id(specie.getId())
                .name(specie.getName())
                .build();

        try {
            restTemplate.postForEntity("http://localhost:8081/api/animals/species", specieDTO, Void.class);
        } catch (Exception e) {

            System.err.println("Błąd przy wysyłaniu danych do animal-service: " + e.getMessage());
        }
    }
}
