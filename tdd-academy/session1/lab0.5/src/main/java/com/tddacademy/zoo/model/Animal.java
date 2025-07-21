package com.tddacademy.zoo.model;

import java.time.LocalDate;

public class Animal {
    private Long id;
    private String name;
    private String species;
    private Double weight;
    private LocalDate dateOfBirth;
    private String healthStatus;

    public Animal() {}

    public Animal(String name, String species, Double weight, LocalDate dateOfBirth, String healthStatus) {
        this.name = name;
        this.species = species;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", weight=" + weight +
                ", dateOfBirth=" + dateOfBirth +
                ", healthStatus='" + healthStatus + '\'' +
                '}';
    }
} 