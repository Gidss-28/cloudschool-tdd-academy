package com.tddacademy.zoo.service;

import com.tddacademy.zoo.model.Animal;
import java.util.List;
import java.util.Optional;

public class AnimalService {
    
    private final AnimalRepository animalRepository;
    
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }
    
    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }
    
    public Optional<Animal> getAnimalById(Long id) {
        return animalRepository.findById(id);
    }
    
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }
    
    public List<Animal> getAnimalsBySpecies(String species) {
        return animalRepository.findBySpecies(species);
    }
    
    public boolean deleteAnimal(Long id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public int getAnimalCount() {
        return animalRepository.count();
    }
    
    public boolean isAnimalHealthy(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        return animal.map(a -> "Healthy".equals(a.getHealthStatus())).orElse(false);
    }
    
    public double getAverageWeight() {
        List<Animal> animals = animalRepository.findAll();
        if (animals.isEmpty()) {
            return 0.0;
        }
        
        double totalWeight = animals.stream()
                .mapToDouble(Animal::getWeight)
                .sum();
        
        return totalWeight / animals.size();
    }
} 