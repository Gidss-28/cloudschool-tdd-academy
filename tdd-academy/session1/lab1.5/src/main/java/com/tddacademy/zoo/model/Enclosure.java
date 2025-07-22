package com.tddacademy.zoo.model;

import java.util.List;

public record Enclosure(
    Long id,
    String name,
    String type,
    Double area,
    String climate,
    List<Animal> animals
) {
    public Enclosure {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Enclosure name cannot be null or empty");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Enclosure type cannot be null or empty");
        }
        if (area != null && area <= 0) {
            throw new IllegalArgumentException("Enclosure area must be positive");
        }
    }
} 