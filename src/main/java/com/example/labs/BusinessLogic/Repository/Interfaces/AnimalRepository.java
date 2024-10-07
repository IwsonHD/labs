package com.example.labs.BusinessLogic.Repository.Interfaces;

import com.example.labs.BusinessLogic.Models.Animal;
import com.example.labs.BusinessLogic.Models.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {
    List<Animal> findBySpecie(Specie specie);
    Optional<Animal> findById(UUID id);
}
