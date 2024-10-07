package com.example.labs.Controllers;

import com.example.labs.BusinessLogic.DTO.Animal.AnimalCollectionRead;
import com.example.labs.BusinessLogic.DTO.Animal.AnimalCreateUpdate;
import com.example.labs.BusinessLogic.DTO.Animal.AnimalRead;
import com.example.labs.BusinessLogic.Models.Animal;
import com.example.labs.BusinessLogic.Models.Specie;
import com.example.labs.BusinessLogic.Services.AnimalService;
import com.example.labs.BusinessLogic.Services.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final AnimalService animalService;
    private final SpecieService specieService;

    @Autowired
    public AnimalController(AnimalService animalService, SpecieService specieService){
        this.animalService = animalService;
        this.specieService = specieService;
    }

    @GetMapping
    public ResponseEntity<AnimalCollectionRead> getAllAnimals(){
        List<Animal> animals = animalService.findAll();
        List<AnimalCollectionRead.Animal> animalList = animals.stream()
                .map(animal -> AnimalCollectionRead.Animal.builder()
                        .id(animal.getId())
                        .age(animal.getAge())
                        .specieName(animal.getSpecie().getName())
                        .weight(animal.getWeight())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(AnimalCollectionRead.builder().animals(animalList).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalRead> getAnimalById(@PathVariable UUID id){
        return animalService.findById(id)
                .map(animal -> AnimalRead.builder()
                        .id(animal.getId())
                        .age(animal.getAge())
                        .specieName(animal.getSpecie().getName())
                        .weight(animal.getWeight())
                        .build())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/specie/{specie_name}")
    public ResponseEntity<AnimalRead> createAnimal(@PathVariable String specie_name,@RequestBody AnimalCreateUpdate animalCreateUpdate){
        var specie = specieService.findByName(specie_name);
        if(specie.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Specie animalSpecie = specie.get();

        Animal newAnimal = Animal.builder()
                .id(UUID.randomUUID())
                .specie(animalSpecie)
                .age(animalCreateUpdate.getAge())
                .weight(animalCreateUpdate.getWeight())
                .build();
        animalService.save(newAnimal);

        return ResponseEntity.status(HttpStatus.CREATED).body(AnimalRead.builder()
                .id(newAnimal.getId())
                .age(newAnimal.getAge())
                .weight(newAnimal.getWeight())
                .specieName(newAnimal.getSpecie().getName())
                .build());
    }

    @GetMapping("species/{specie_name}")
    public ResponseEntity<AnimalCollectionRead> getAnimalsBySpecieName(@PathVariable String specie_name){
        var specie = specieService.findByName(specie_name);
        if(specie.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        Specie animalSpecie = specie.get();

        if(animalSpecie.getAnimals().isEmpty())
            return ResponseEntity.noContent().build();

        List<AnimalCollectionRead.Animal> animalList = animalSpecie.getAnimals().stream()
                .map(animal -> AnimalCollectionRead.Animal.builder()
                        .id(animal.getId())
                        .specieName(animal.getSpecie().getName())
                        .age(animal.getAge())
                        .weight(animal.getWeight())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(AnimalCollectionRead.builder().animals(animalList).build());
    }


}
