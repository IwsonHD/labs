package com.example.labs.Controllers;


import com.example.labs.BusinessLogic.DTO.Specie.SpecieCreateUpdate;
import com.example.labs.BusinessLogic.DTO.Specie.SpecieRead;
import com.example.labs.BusinessLogic.DTO.Specie.SpecieCollectionRead;
import com.example.labs.BusinessLogic.Models.Specie;
import com.example.labs.BusinessLogic.Services.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/species")
public class SpecieController {
    private final SpecieService specieService;

    @Autowired
    public SpecieController(SpecieService specieService){this.specieService = specieService;}

    @GetMapping
    public ResponseEntity<SpecieCollectionRead> getAllSpecies(){
        List<Specie> species = specieService.findAll();
        List<SpecieCollectionRead.Specie> specieDTOList = species.stream()
                .map(specie -> SpecieCollectionRead.Specie.builder()
                        .id(specie.getId())
                        .name(specie.getName())
                        .occurring(specie.getOccurring())
                        .dietType(specie.getDietType())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(SpecieCollectionRead.builder().species(specieDTOList).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecieRead> getSpecieById(@PathVariable UUID id){
        return specieService.findById(id)
                .map(specie -> SpecieRead.builder()
                        .name(specie.getName())
                        .occurring(specie.getOccurring())
                        .dietType(specie.getDietType())
                        .id(specie.getId())
                        .build())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<SpecieRead> createSpecie(@RequestBody SpecieCreateUpdate specieCreateUpdate){
        Specie newSpecie = Specie.builder()
                .id(UUID.randomUUID())
                .name(specieCreateUpdate.getName())
                .occurring(specieCreateUpdate.getOccurring())
                .dietType(specieCreateUpdate.getDietType())
                .build();
        specieService.save(newSpecie);

        SpecieRead specieRead = SpecieRead.builder()
                .name(newSpecie.getName())
                .dietType(newSpecie.getDietType())
                .occurring(newSpecie.getOccurring())
                .id(newSpecie.getId())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(specieRead);
    }

    @PostMapping("/{id}")
    public ResponseEntity<SpecieRead> updateSpecie(@PathVariable UUID id, @RequestBody SpecieCreateUpdate specieCreateUpdate){
        return specieService.findById(id)
                .map(specie -> {
                    specie.setName(specieCreateUpdate.getName());
                    specie.setOccurring(specieCreateUpdate.getOccurring());
                    specie.setDietType(specieCreateUpdate.getDietType());
                    specieService.save(specie);

                    return ResponseEntity.ok(SpecieRead.builder()
                            .id(specie.getId())
                            .occurring(specie.getOccurring())
                            .name(specie.getName())
                            .dietType(specie.getDietType())
                            .build());
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecie(@PathVariable UUID id){
        if(specieService.findById(id).isPresent()){
            specieService.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
