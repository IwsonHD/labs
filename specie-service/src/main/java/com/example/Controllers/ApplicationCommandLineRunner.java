package com.example.Controllers;

import com.example.BusinessLogic.Enums.Continents;
import com.example.BusinessLogic.Enums.DietType;
import com.example.BusinessLogic.Models.Specie;
import com.example.BusinessLogic.Services.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {
    private final SpecieService specieService;
    private final RestTemplate restTemplate;

    public ApplicationCommandLineRunner(SpecieService specieService, RestTemplateBuilder restTemplateBuilder) {
        this.specieService = specieService;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Welcome in app where you can list species.");
        System.out.println("Available commands: list --commands, list --categories, save --category, delete --category, stop");

        boolean running = true;

        while (running) {
            System.out.print("# ");
            command = scanner.nextLine();
            switch (command) {
                case "list --commands":
                    listCommands();
                    break;
                case "list --categories":
                    listCategories();
                    break;
                case "save --category":
                    saveCategory(scanner);
                    break;
                case "delete --category":
                    deleteCategory(scanner);
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
        System.out.println("Available commands: list --commands, list --categories, save --category, delete --category, stop");
    }

    private void listCategories() {
        List<Specie> species = specieService.findAll();
        System.out.println("Available categories: ");
        species.forEach(System.out::println);
    }

    private void saveCategory(Scanner scanner) {
        System.out.println("Input category name:");
        String categoryName = scanner.nextLine().toUpperCase();

        if (specieService.findByName(categoryName).isEmpty()) {

            Specie newCategory = Specie.builder()
                    .id(UUID.randomUUID())
                    .name(categoryName)
                    .dietType(DietType.values()[new Random().nextInt(DietType.values().length)])
                    .occurring(Continents.values()[new Random().nextInt(Continents.values().length)])
                    .build();

            specieService.save(newCategory);
            System.out.println("New category added: " + newCategory);

            // Notify animal-service about the new category
            notifyAnimalService(newCategory);
        } else {
            System.out.println("Category with name " + categoryName + " already exists.");
        }
    }

    private void deleteCategory(Scanner scanner) {
        System.out.println("Input category ID to delete:");
        String input = scanner.nextLine();
        try {
            UUID id = UUID.fromString(input);
            if (specieService.findById(id).isPresent()) {
                specieService.deleteById(id);
                System.out.println("Category with ID " + id + " deleted.");

                // Notify animal-service about the deletion
                notifyAnimalService(id);
            } else {
                System.out.println("Category with ID " + id + " not found.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid UUID format. Operation aborted.");
        }
    }

    private void notifyAnimalService(Specie newCategory) {
        String url = "http://localhost:8081/api/animals/species"; // Adjust the URL according to your endpoint
        ResponseEntity<Void> response = restTemplate.postForEntity(url, newCategory, Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Notified animal-service about new category with ID: " + newCategory.getId());
        } else {
            System.out.println("Failed to notify animal-service. Response code: " + response.getStatusCode());
        }
    }

    private void notifyAnimalService(UUID id) {
        String url = "http://localhost:8081/api/animals/species/" + id; // Adjust the URL according to your endpoint
        restTemplate.delete(url);
        System.out.println("Notified animal-service to remove category with ID: " + id);
    }
}
