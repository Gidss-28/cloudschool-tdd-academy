package com.tddacademy.zoo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class ZooTest {

    @Test
    @DisplayName("Should create a valid Zoo with all required fields")
    void shouldCreateValidZoo() {
        // Given
        Long id = 1L;
        String name = "Central Park Zoo";
        String location = "New York, NY";
        String description = "A beautiful zoo in the heart of Manhattan";
        List<Enclosure> enclosures = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        // When
        Zoo zoo = new Zoo(id, name, location, description, enclosures, people);

        // Then
        assertNotNull(zoo);
        assertEquals(id, zoo.id());
        assertEquals(name, zoo.name());
        assertEquals(location, zoo.location());
        assertEquals(description, zoo.description());
        assertEquals(enclosures, zoo.enclosures());
        assertEquals(people, zoo.people());
    }

    @Test
    @DisplayName("Should throw exception when zoo name is null")
    void shouldThrowExceptionWhenNameIsNull() {
        // Given
        Long id = 1L;
        String name = null;
        String location = "New York, NY";
        String description = "A beautiful zoo in the heart of Manhattan";
        List<Enclosure> enclosures = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Zoo(id, name, location, description, enclosures, people)
        );
        assertEquals("Zoo name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when zoo name is empty")
    void shouldThrowExceptionWhenNameIsEmpty() {
        // Given
        Long id = 1L;
        String name = "";
        String location = "New York, NY";
        String description = "A beautiful zoo in the heart of Manhattan";
        List<Enclosure> enclosures = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Zoo(id, name, location, description, enclosures, people)
        );
        assertEquals("Zoo name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when zoo location is null")
    void shouldThrowExceptionWhenLocationIsNull() {
        // Given
        Long id = 1L;
        String name = "Central Park Zoo";
        String location = null;
        String description = "A beautiful zoo in the heart of Manhattan";
        List<Enclosure> enclosures = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Zoo(id, name, location, description, enclosures, people)
        );
        assertEquals("Zoo location cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when zoo location is empty")
    void shouldThrowExceptionWhenLocationIsEmpty() {
        // Given
        Long id = 1L;
        String name = "Central Park Zoo";
        String location = "   ";
        String description = "A beautiful zoo in the heart of Manhattan";
        List<Enclosure> enclosures = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Zoo(id, name, location, description, enclosures, people)
        );
        assertEquals("Zoo location cannot be null or empty", exception.getMessage());
    }
} 