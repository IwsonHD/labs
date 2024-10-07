//package com.example.labs.Entity.Repository.InMemory;
//
//import com.example.labs.DataStore.DataStore;
//import com.example.labs.Entity.Specie;
//import com.example.labs.Enums.Continents;
//import com.example.labs.Entity.Repository.Interfaces.SpeciesRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Repository
//public class SpeciesRepositoryInMemory implements SpeciesRepository {
//    private final DataStore dataStore;
//
//    @Autowired
//    public SpeciesRepositoryInMemory(DataStore dataStore){this.dataStore = dataStore;}
//
//    @Override
//    public Optional<Specie> findById(UUID id){
//        return dataStore.findAllSpecies().stream()
//                .filter(specie -> specie.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public List<Specie> findByContinent(Continents continent){
//        return dataStore.findAllSpecies().stream()
//                .filter(specie -> specie.getOccurring().equals(continent))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Optional<Specie> findByName(String name){
//        return dataStore.findAllSpecies().stream()
//                .filter(specie -> specie.getName().equals(name))
//                .findFirst();
//    }
//
//    @Override
//    public List<Specie> findAll()
//    {
//        return dataStore.findAllSpecies();
//    }
//
//    @Override
//    public void delete(Specie entity){ dataStore.deleteAnimal(entity.getId());}
//
//    @Override
//    public Specie save(Specie specie){
//        dataStore.saveSpecies(specie);
//        return specie;
//    }
//}
