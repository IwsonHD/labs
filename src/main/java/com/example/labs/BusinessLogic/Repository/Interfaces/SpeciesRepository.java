package com.example.labs.BusinessLogic.Repository.Interfaces;

import com.example.labs.BusinessLogic.Models.Specie;
import com.example.labs.Enums.Continents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface SpeciesRepository extends JpaRepository<Specie, UUID> {
    Optional<Specie> findById(UUID id);
    List<Specie> findByOccurring(Continents continent);
    Optional<Specie> findByName(String name);
}
