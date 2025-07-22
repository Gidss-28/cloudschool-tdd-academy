package com.tddacademy.zoo.model;

import java.time.LocalDate;

public record Person(
    Long id,
    String firstName,
    String lastName,
    String role,
    String email,
    LocalDate hireDate,
    Double salary
) {
    public Person {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Person first name cannot be null or empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Person last name cannot be null or empty");
        }
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Person role cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Person email cannot be null or empty");
        }
        if (salary != null && salary <= 0) {
            throw new IllegalArgumentException("Person salary must be positive");
        }
    }
} 