package com.tddacademy.zoo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name = "animals")
public class Animal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Animal name cannot be null or empty")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Animal species cannot be null or empty")
    @Column(nullable = false)
    private String species;
    
    @Column
    private String breed;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Positive(message = "Animal weight must be positive")
    @Column
    private Double weight;
    
    @Column(name = "health_status")
    private String healthStatus;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enclosure_id")
    private Enclosure enclosure;
    
    // Default constructor for JPA
    protected Animal() {}
    
    public Animal(String name, String species, String breed, LocalDate dateOfBirth, Double weight, String healthStatus) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.healthStatus = healthStatus;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSpecies() {
        return species;
    }
    
    public void setSpecies(String species) {
        this.species = species;
    }
    
    public String getBreed() {
        return breed;
    }
    
    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Double getWeight() {
        return weight;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public String getHealthStatus() {
        return healthStatus;
    }
    
    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }
    
    public Enclosure getEnclosure() {
        return enclosure;
    }
    
    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }
} 