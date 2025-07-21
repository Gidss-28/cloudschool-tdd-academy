package com.tddacademy.zoo.service;

import com.tddacademy.zoo.model.Animal;
import java.util.List;
import java.util.Optional;

public class ZooManager {
    
    private final AnimalService animalService;
    private final NotificationService notificationService;
    
    public ZooManager(AnimalService animalService, NotificationService notificationService) {
        this.animalService = animalService;
        this.notificationService = notificationService;
    }
    
    public Animal addNewAnimal(Animal animal) {
        Animal savedAnimal = animalService.createAnimal(animal);
        
        // Notify staff about new animal
        notificationService.sendEmail("staff@zoo.com", 
            "New Animal Added", 
            "New animal " + animal.getName() + " has been added to the zoo.");
        
        return savedAnimal;
    }
    
    public boolean removeAnimal(Long animalId) {
        Optional<Animal> animal = animalService.getAnimalById(animalId);
        
        if (animal.isPresent()) {
            boolean deleted = animalService.deleteAnimal(animalId);
            
            if (deleted) {
                notificationService.sendSMS("+1234567890", 
                    "Animal " + animal.get().getName() + " has been removed from the zoo.");
                return true;
            }
        }
        
        return false;
    }
    
    public void checkAnimalHealth(Long animalId) {
        if (!animalService.isAnimalHealthy(animalId)) {
            notificationService.sendEmail("vet@zoo.com", 
                "Animal Health Alert", 
                "Animal with ID " + animalId + " needs medical attention.");
        }
    }
    
    public int getTotalAnimals() {
        return animalService.getAnimalCount();
    }
    
    public double getAverageAnimalWeight() {
        return animalService.getAverageWeight();
    }
    
    public List<Animal> getAnimalsBySpecies(String species) {
        return animalService.getAnimalsBySpecies(species);
    }
} 