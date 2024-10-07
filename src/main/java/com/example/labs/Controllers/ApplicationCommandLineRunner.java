package com.example.labs.Controllers;

import com.example.labs.BusinessLogic.Models.Animal;
import com.example.labs.BusinessLogic.Models.Specie;
import com.example.labs.BusinessLogic.Services.AnimalService;
import com.example.labs.BusinessLogic.Services.SpecieService;
import com.example.labs.Enums.Continents;
import com.example.labs.Enums.DietType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {
    private final AnimalService animalService;
    private final SpecieService specieService;

    @Autowired
    public ApplicationCommandLineRunner(AnimalService animalService, SpecieService specieService){
        this.animalService = animalService;
        this.specieService = specieService;
    }

    @Override
    public void run(String... args){
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Welcome in app where you can list species and their representatives");
        System.out.println("Available commands: list --commands, list --species, list --animals, save --element, delete --element, stop");

        boolean running = true;

        while(running){
            System.out.print("# ");
            command = scanner.nextLine();
            switch (command){
                case "list --commands":
                    listCommands();
                    break;
                case "list --species":
                    listSpecies();
                    break;
                case "list --animals":
                    listAnimals();
                    break;
                case "save --element":
                    saveElement(scanner);
                    break;
                case "delete --element":
                    deleteElement(scanner);
                    break;
                case "stop":
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        }
        scanner.close();
    }

    private void listCommands(){
        System.out.println("Available commands: list --commands, list --species, list --animals, save --element, delete --element, stop");
    }

    private void listSpecies(){
        List<Specie> species = specieService.findAll();
        System.out.println("Available species: ");
        species.forEach(specie -> System.out.println(specie.getName()));
    }

    private void listAnimals(){
        List<Animal> animals = animalService.findAll();
        animals.forEach(System.out::println);
    }

    private void saveElement(Scanner scanner){
        System.out.println("Input specie name:");
        String specieName = scanner.nextLine().toUpperCase();

        if(specieService.findByName(specieName).isEmpty()){
            System.out.println("Input new specie ID:");
            String newSpecieId = scanner.nextLine();
            UUID id;
            try{
                id = UUID.fromString(newSpecieId);
            } catch(IllegalArgumentException e){
                System.out.println("Wrong input for UUID. Operation aborted.");
                return;
            }

            Specie newSpecie = Specie.builder()
                    .id(id)
                    .name(specieName)
                    .dietType(DietType.values()[new Random().nextInt(DietType.values().length)])
                    .occurring(Continents.values()[new Random().nextInt(Continents.values().length)])
                    .build();

            specieService.save(newSpecie);
            System.out.println("New specie added: " + newSpecie);

            System.out.println("Input animal age:");
            int age;
            try {
                age = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid age input. Operation aborted.");
                return;
            }

            Animal newAnimal = Animal.builder()
                    .id(UUID.randomUUID())
                    .specie(newSpecie)
                    .age(age)
                    .build();

            animalService.save(newAnimal);
            System.out.println("New animal added: " + newAnimal);
        } else {
            System.out.println("Specie with name " + specieName + " already exists.");
        }
    }

    private void deleteElement(Scanner scanner){
        System.out.println("Input animal ID to delete:");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            if (animalService.findById(id).isPresent()) {
                animalService.deleteById(id);
                System.out.println("Animal with ID " + id + " deleted.");
            } else {
                System.out.println("Animal with ID " + id + " not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format. Operation aborted.");
        }
    }
}
