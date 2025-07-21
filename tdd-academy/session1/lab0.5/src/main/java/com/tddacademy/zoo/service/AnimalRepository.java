package com.tddacademy.zoo.service;

import com.tddacademy.zoo.model.Animal;
import java.util.List;
import java.util.Optional;

public interface AnimalRepository {
    
    Animal save(Animal animal);
    
    Optional<Animal> findById(Long id);
    
    List<Animal> findAll();
    
    List<Animal> findBySpecies(String species);
    
    void deleteById(Long id);
    
    boolean existsById(Long id);
    
    int count();
} 