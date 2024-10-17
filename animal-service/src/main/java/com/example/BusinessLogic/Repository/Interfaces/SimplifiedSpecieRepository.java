package com.example.BusinessLogic.Repository.Interfaces;

import com.example.BusinessLogic.Models.SimplifiedSpecie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SimplifiedSpecieRepository extends JpaRepository<SimplifiedSpecie, UUID> {
}
