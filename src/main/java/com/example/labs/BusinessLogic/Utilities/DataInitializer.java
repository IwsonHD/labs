package com.example.labs.BusinessLogic.Utilities;

import com.example.labs.BusinessLogic.Models.Animal;
import com.example.labs.BusinessLogic.Models.Specie;
import com.example.labs.BusinessLogic.Services.AnimalService;
import com.example.labs.BusinessLogic.Services.SpecieService;
import com.example.labs.BusinessLogic.Enums.Continents;
import com.example.labs.BusinessLogic.Enums.DietType;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


import java.util.UUID;

@Component
public class DataInitializer {

    private final AnimalService animalService;
    private final SpecieService specieService;

    public DataInitializer(AnimalService animalService, SpecieService specieService) {
        this.animalService = animalService;
        this.specieService = specieService;
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


            Animal lion1 = Animal.builder()
                    .id(UUID.randomUUID())
                    .specie(lionSpecie)
                    .age(5)
                    .weight(190.5f)
                    .build();

            Animal lion2 = Animal.builder()
                    .id(UUID.randomUUID())
                    .specie(lionSpecie)
                    .age(6)
                    .weight(210.0f)
                    .build();

            Animal elephant1 = Animal.builder()
                    .id(UUID.randomUUID())
                    .specie(elephantSpecie)
                    .age(10)
                    .weight(5000.0f)
                    .build();

            Animal elephant2 = Animal.builder()
                    .id(UUID.randomUUID())
                    .specie(elephantSpecie)
                    .age(12)
                    .weight(5200.0f)
                    .build();

            Animal zebra1 = Animal.builder()
                    .id(UUID.randomUUID())
                    .specie(zebraSpecie)
                    .age(3)
                    .weight(300.5f)
                    .build();

            Animal zebra2 = Animal.builder()
                    .id(UUID.randomUUID())
                    .specie(zebraSpecie)
                    .age(4)
                    .weight(320.0f)
                    .build();


            animalService.save(lion1);
            animalService.save(lion2);
            animalService.save(elephant1);
            animalService.save(elephant2);
            animalService.save(zebra1);
            animalService.save(zebra2);
        }
    }
}
