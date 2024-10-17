package com.example.BusinessLogic.Repository.Interfaces;

import com.example.BusinessLogic.Enums.Continents;
import com.example.BusinessLogic.Models.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Specie, UUID> {
    List<Specie> findByOccurring(Continents continent);
    Optional<Specie> findByName(String name);
}
