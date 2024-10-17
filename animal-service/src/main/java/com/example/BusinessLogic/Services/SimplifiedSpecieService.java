package com.example.BusinessLogic.Services;

import com.example.BusinessLogic.Models.SimplifiedSpecie;
import com.example.BusinessLogic.Repository.Interfaces.SimplifiedSpecieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SimplifiedSpecieService {

    private final SimplifiedSpecieRepository simplifiedSpecieRepository;

    @Autowired
    public SimplifiedSpecieService(SimplifiedSpecieRepository simplifiedSpecieRepository) {
        this.simplifiedSpecieRepository = simplifiedSpecieRepository;
    }

    public List<SimplifiedSpecie> findAll() {
        return simplifiedSpecieRepository.findAll();
    }

    public Optional<SimplifiedSpecie> findById(UUID id) {
        return simplifiedSpecieRepository.findById(id);
    }

    public SimplifiedSpecie save(SimplifiedSpecie simplifiedSpecie) {
        return simplifiedSpecieRepository.save(simplifiedSpecie);
    }

    public void deleteById(UUID id) {
        simplifiedSpecieRepository.deleteById(id);
    }

    public Optional<SimplifiedSpecie> findByName(String name) {
        return simplifiedSpecieRepository.findAll()
                .stream()
                .filter(specie -> specie.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
