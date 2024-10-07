package com.example.labs.UnusedYet.DataStore;

import com.example.labs.BusinessLogic.Models.Animal;
import com.example.labs.BusinessLogic.Models.Specie;
import com.example.labs.UnusedYet.Utilities.CloningUtility;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Log
@Component
public class DataStore {
    private final Set<Specie> species = new HashSet<>();

    private final Set<Animal> animals = new HashSet<>();

    private final CloningUtility cloningUtiliy;

    @Autowired
    public DataStore(CloningUtility cloningUtiliy) {this.cloningUtiliy = cloningUtiliy;}

    public synchronized List<Specie> findAllSpecies(){
        return species.stream()
                .map(s -> cloningUtiliy.clone(s))
                .collect(Collectors.toList());
    }

    public synchronized void saveSpecies(Specie entity){
        species.removeIf(specie -> specie.getId().equals(entity.getId()));
        species.add(cloningUtiliy.clone(entity));
    }

    public synchronized List<Animal> findAllAnimals(){
        return animals.stream()
                .map(a -> cloningUtiliy.clone(a))
                .collect(Collectors.toList());
    }

    public synchronized void saveAnimal(Animal entity) {
        if(entity.getSpecie() != null){
            species.stream()
                    .filter( specie -> specie.getId().equals(entity.getSpecie().getId()))
                    .findFirst()
                    .ifPresentOrElse(
                            entity::setSpecie,
                            ()-> {
                                throw new IllegalArgumentException("No specie with id\"%s\"".formatted(entity.getSpecie().getId()));
                            }

                    );
        }
        animals.removeIf(animal -> animal.getId().equals(entity.getId()));
        animals.add(entity);
    }

    public synchronized void deleteAnimal(UUID id) throws IllegalArgumentException {
        if(!animals.removeIf(animal -> animal.getId().equals(id))){
            throw new IllegalArgumentException("The animal with id \"%s\" does not exist".formatted(id));
        }
    }

}
