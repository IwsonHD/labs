package com.example.Controllers;

import com.example.BusinessLogic.DTO.AnimalCollectionRead;
import com.example.BusinessLogic.DTO.AnimalCreateUpdate;
import com.example.BusinessLogic.DTO.AnimalRead;
import com.example.BusinessLogic.DTO.SimplifiedSpecieDTO;
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
@RequestMapping("/api/animals/species")
public class SimplifiedSpecieController {
    private final AnimalService animalService;
    private final SimplifiedSpecieService simplifiedSpecieService;

    @Autowired
    public SimplifiedSpecieController(AnimalService animalService, SimplifiedSpecieService simplifiedSpecieService) {
        this.animalService = animalService;
        this.simplifiedSpecieService = simplifiedSpecieService;
    }

    @PostMapping
    public ResponseEntity<Void> addSpecie(@RequestBody SimplifiedSpecieDTO simplifiedSpecie){
        if(simplifiedSpecieService.findById(simplifiedSpecie.getId()).isEmpty()){
            SimplifiedSpecie newSpecie = SimplifiedSpecie.builder()
                    .id(simplifiedSpecie.getId())
                    .name(simplifiedSpecie.getName())
                    .build();
            simplifiedSpecieService.save(newSpecie);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeSpecie(@PathVariable UUID id){
        var specieToDel = simplifiedSpecieService.findById(id);
        if(specieToDel.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        animalService.findBySpecie(specieToDel.get()).stream()
                .map(animal -> {
                    System.out.println(animal.getId());
                    return animal.getId();
                }
                )
                .forEach(animalService::deleteById);
        simplifiedSpecieService.deleteById(specieToDel.get().getId());
        return ResponseEntity.noContent().build();
    }
}
