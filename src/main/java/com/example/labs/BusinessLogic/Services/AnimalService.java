package com.example.labs.BusinessLogic.Services;


import com.example.labs.BusinessLogic.Models.Animal;
import com.example.labs.BusinessLogic.Repository.Interfaces.AnimalRepository;
import com.example.labs.BusinessLogic.Models.Specie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository){ this.animalRepository = animalRepository;}

    public List<Animal> findAll() {
          return animalRepository.findAll();
    }

    public Optional<Animal> findById(UUID id) {
        return animalRepository.findById(id);
    }

    public List<Animal> findBySpecie(Specie specie){
        return animalRepository.findBySpecie(specie);
    }

    public Animal save(Animal animal){
        return animalRepository.save(animal);
    }

    public void deleteById(UUID id){
        animalRepository.deleteById(id);
    }

}
