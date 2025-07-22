package com.tddacademy.zoo.model;

import java.time.LocalDate;

public record Animal(
    Long id,
    String name,
    String species,
    String breed,
    LocalDate dateOfBirth,
    Double weight,
    String healthStatus
) {
    public Animal {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Animal name cannot be null or empty");
        }
        if (species == null || species.trim().isEmpty()) {
            throw new IllegalArgumentException("Animal species cannot be null or empty");
        }
        if (weight != null && weight <= 0) {
            throw new IllegalArgumentException("Animal weight must be positive");
        }
    }
} 