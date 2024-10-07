package com.example.labs.BusinessLogic.Services;

import com.example.labs.BusinessLogic.Repository.Interfaces.SpeciesRepository;
import com.example.labs.BusinessLogic.Models.Specie;
import com.example.labs.BusinessLogic.Enums.Continents;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SpecieService {

    private final SpeciesRepository speciesRepository;

    public SpecieService(SpeciesRepository speciesRepository) {
        this.speciesRepository = speciesRepository;
    }

    public List<Specie> findAll() {
        return speciesRepository.findAll();
    }

    public Optional<Specie> findById(UUID id) {
        return speciesRepository.findById(id);
    }

    public List<Specie> findByOccurring(Continents continent) {
        return speciesRepository.findByOccurring(continent);
    }

    public Optional<Specie> findByName(String name) {
        return speciesRepository.findByName(name);
    }

    public Specie save(Specie specie) {
        return speciesRepository.save(specie);
    }

    public void deleteById(UUID id) {
        speciesRepository.deleteById(id);
    }
}