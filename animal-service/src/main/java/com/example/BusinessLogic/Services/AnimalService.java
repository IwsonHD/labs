package com.example.BusinessLogic.Services;


import com.example.BusinessLogic.Models.Animal;
import com.example.BusinessLogic.Models.SimplifiedSpecie;
import com.example.BusinessLogic.Repository.Interfaces.AnimalRepository;
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

    public List<Animal> findBySpecie(SimplifiedSpecie specie){
        return animalRepository.findBySpecie(specie);
    }

    public Animal save(Animal animal){
        return animalRepository.save(animal);
    }

    public void deleteById(UUID id){
        animalRepository.deleteById(id);
    }

}
