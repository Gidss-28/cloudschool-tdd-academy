package com.tddacademy.zoo.model;

import java.util.List;

public record Zoo(
    Long id,
    String name,
    String location,
    String description,
    List<Enclosure> enclosures,
    List<Person> people
) {
    public Zoo {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Zoo name cannot be null or empty");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Zoo location cannot be null or empty");
        }
    }
} 