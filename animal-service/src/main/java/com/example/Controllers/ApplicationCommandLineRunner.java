package com.example.Controllers;

import com.example.BusinessLogic.Models.Animal;
import com.example.BusinessLogic.Models.SimplifiedSpecie;
import com.example.BusinessLogic.Services.AnimalService;
import com.example.BusinessLogic.Services.SimplifiedSpecieService;
import org.hibernate.type.descriptor.sql.internal.Scale6IntervalSecondDdlType;
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
    private final SimplifiedSpecieService simplifiedSpecieService;
    @Autowired
    public ApplicationCommandLineRunner(AnimalService animalService, SimplifiedSpecieService simplifiedSpecieService){
        this.animalService = animalService;
        this.simplifiedSpecieService = simplifiedSpecieService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Welcome in app where you can list and manage animals.");
        listCommands();

        boolean running = true;

        while (running) {
            System.out.print("# ");
            command = scanner.nextLine();
            switch (command) {
                case "list --commands":
                    listCommands();
                    break;
                case "list --animals":
                    listAnimals();
                    break;
                case "list --species":
                    listSpecies();
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

    private void listCommands() {
        System.out.println("Available commands: list --commands, list --animals, list --species, save --element, delete --element, stop");
    }

    private void listAnimals() {
        List<Animal> animals = animalService.findAll();
        animals.forEach(System.out::println);
    }
    private void listSpecies(){
        List<SimplifiedSpecie> species = simplifiedSpecieService.findAll();
        species.forEach(System.out::println);
    }

    private void saveElement(Scanner scanner) {
        System.out.println("Input specie name:");
        String specieName = scanner.nextLine().toUpperCase();

        var ifSpecie = simplifiedSpecieService.findByName(specieName);

        if(ifSpecie.isEmpty()){
            System.out.println("No such specie exists in data base");
            return;
        }
        var specie = ifSpecie.get();

        System.out.println("Input animal age:");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age input. Operation aborted.");
            return;
        }

        System.out.println("Input animal weight:");
        float weight;
        try {
            weight = Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid weight input. Operation aborted.");
            return;
        }

        Animal newAnimal = Animal.builder()
                .id(UUID.randomUUID())
                .specie(specie)
                .age(age)
                .weight(weight)
                .build();

        animalService.save(newAnimal);
        System.out.println("New animal added: " + newAnimal);
    }

    private void deleteElement(Scanner scanner) {
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
