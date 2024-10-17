package com.example.BusinessLogic.Repository.Interfaces;

import com.example.BusinessLogic.Models.Animal;
import com.example.BusinessLogic.Models.SimplifiedSpecie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.SimpleTimeZone;
import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {
    List<Animal> findBySpecie(SimplifiedSpecie specie);
}