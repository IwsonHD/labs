package com.example.Controllers;

import com.example.BusinessLogic.DTO.AnimalCollectionRead;
import com.example.BusinessLogic.DTO.AnimalCreateUpdate;
import com.example.BusinessLogic.DTO.AnimalRead;
import com.example.BusinessLogic.Models.Animal;
import com.example.BusinessLogic.Models.SimplifiedSpecie;
import com.example.BusinessLogic.Services.AnimalService;
import com.example.BusinessLogic.Services.SimplifiedSpecieService;
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
    private final SimplifiedSpecieService simplifiedSpecieService;

    @Autowired
    public AnimalController(AnimalService animalService, SimplifiedSpecieService simplifiedSpecieService) {
        this.animalService = animalService;
        this.simplifiedSpecieService = simplifiedSpecieService;
    }

    @GetMapping
    public ResponseEntity<AnimalCollectionRead> getAllAnimals() {
        List<Animal> animals = animalService.findAll();
        List<AnimalCollectionRead.Animal> animalList = animals.stream()
                .map(animal -> AnimalCollectionRead.Animal.builder()
                        .id(animal.getId())
                        .age(animal.getAge())
                        .specieName(animal.getSpecie().getName()) // Używamy .getName() do uzyskania nazwy gatunku
                        .weight(animal.getWeight())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(AnimalCollectionRead.builder().animals(animalList).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalRead> getAnimalById(@PathVariable UUID id) {
        return animalService.findById(id)
                .map(animal -> AnimalRead.builder()
                        .id(animal.getId())
                        .age(animal.getAge())
                        .specieName(animal.getSpecie().getName()) // Używamy .getName() do uzyskania nazwy gatunku
                        .weight(animal.getWeight())
                        .build())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/specie/{specieId}")
    public ResponseEntity<AnimalRead> createAnimal(@PathVariable UUID specieId, @RequestBody AnimalCreateUpdate animalCreateUpdate) {
        SimplifiedSpecie specie = simplifiedSpecieService.findById(specieId)
                .orElseThrow(() -> new RuntimeException("Specie not found"));

        Animal newAnimal = Animal.builder()
                .id(UUID.randomUUID())
                .specie(specie)
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

    @GetMapping("specie/{specieId}")
    public ResponseEntity<AnimalCollectionRead> getAnimalsBySpecieId(@PathVariable UUID specieId) {
        SimplifiedSpecie specie = simplifiedSpecieService.findById(specieId)
                .orElseThrow(() -> new RuntimeException("Specie not found"));

        List<Animal> animalsBySpecie = animalService.findBySpecie(specie);
        if (animalsBySpecie.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<AnimalCollectionRead.Animal> animalList = animalsBySpecie.stream()
                .map(animal -> AnimalCollectionRead.Animal.builder()
                        .id(animal.getId())
                        .specieName(animal.getSpecie().getName()) // Używamy .getName() do uzyskania nazwy gatunku
                        .age(animal.getAge())
                        .weight(animal.getWeight())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(AnimalCollectionRead.builder().animals(animalList).build());
    }

    @PostMapping("/{id}")
    public ResponseEntity<AnimalRead> updateAnimal(@PathVariable UUID id, @RequestBody AnimalCreateUpdate animalCreateUpdate) {
        return animalService.findById(id)
                .map(animal -> {
                    animal.setAge(animalCreateUpdate.getAge());
                    animal.setWeight(animalCreateUpdate.getWeight());
                    animalService.save(animal);

                    return ResponseEntity.ok(AnimalRead.builder()
                            .id(animal.getId())
                            .age(animal.getAge())
                            .weight(animal.getWeight())
                            .specieName(animal.getSpecie().getName()) // Używamy .getName() do uzyskania nazwy gatunku
                            .build());
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable UUID id) {
        if (animalService.findById(id).isPresent()) {
            animalService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
