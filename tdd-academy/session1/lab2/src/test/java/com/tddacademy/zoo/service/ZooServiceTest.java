package com.tddacademy.zoo.service;

import com.tddacademy.zoo.model.Zoo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZooServiceTest {

    private ZooService zooService;

    @BeforeEach
    void setUp() {
        zooService = new ZooService();
    }

    @Test
    @DisplayName("Should return empty list when no zoos exist")
    void shouldReturnEmptyListWhenNoZoosExist() {
        // When
        List<Zoo> zoos = zooService.getAllZoos();

        // Then
        assertNotNull(zoos);
        assertTrue(zoos.isEmpty());
    }

    @Test
    @DisplayName("Should create a new zoo successfully")
    void shouldCreateNewZooSuccessfully() {
        // Given
        Zoo zoo = new Zoo(null, "Manila Zoo", "Manila, Philippines", 
                         "A beautiful zoo in the heart of Manila", new ArrayList<>(), new ArrayList<>());

        // When
        Zoo createdZoo = zooService.createZoo(zoo);

        // Then
        assertNotNull(createdZoo);
        assertEquals(1L, createdZoo.id());
        assertEquals("Manila Zoo", createdZoo.name());
        assertEquals("Manila, Philippines", createdZoo.location());
        assertEquals("A beautiful zoo in the heart of Manila", createdZoo.description());
    }

    @Test
    @DisplayName("Should get zoo by id successfully")
    void shouldGetZooByIdSuccessfully() {
        // Given
        Zoo zoo = new Zoo(null, "Manila Zoo", "Manila, Philippines", 
                         "A beautiful zoo in the heart of Manila", new ArrayList<>(), new ArrayList<>());
        Zoo createdZoo = zooService.createZoo(zoo);

        // When
        Zoo foundZoo = zooService.getZooById(createdZoo.id());

        // Then
        assertNotNull(foundZoo);
        assertEquals(createdZoo.id(), foundZoo.id());
        assertEquals(createdZoo.name(), foundZoo.name());
    }

    @Test
    @DisplayName("Should throw exception when getting non-existent zoo")
    void shouldThrowExceptionWhenGettingNonExistentZoo() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> zooService.getZooById(999L)
        );
        assertEquals("Zoo not found with id: 999", exception.getMessage());
    }

    @Test
    @DisplayName("Should update zoo successfully")
    void shouldUpdateZooSuccessfully() {
        // Given
        Zoo zoo = new Zoo(null, "Manila Zoo", "Manila, Philippines", 
                         "A beautiful zoo in the heart of Manila", new ArrayList<>(), new ArrayList<>());
        Zoo createdZoo = zooService.createZoo(zoo);
        
        Zoo updatedZooData = new Zoo(null, "Updated Zoo Name", "Updated Location", 
                                    "Updated description", new ArrayList<>(), new ArrayList<>());

        // When
        Zoo updatedZoo = zooService.updateZoo(createdZoo.id(), updatedZooData);

        // Then
        assertNotNull(updatedZoo);
        assertEquals(createdZoo.id(), updatedZoo.id());
        assertEquals("Updated Zoo Name", updatedZoo.name());
        assertEquals("Updated Location", updatedZoo.location());
        assertEquals("Updated description", updatedZoo.description());
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent zoo")
    void shouldThrowExceptionWhenUpdatingNonExistentZoo() {
        // Given
        Zoo zoo = new Zoo(null, "Test Zoo", "Test Location", 
                         "Test description", new ArrayList<>(), new ArrayList<>());

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> zooService.updateZoo(999L, zoo)
        );
        assertEquals("Zoo not found with id: 999", exception.getMessage());
    }

    @Test
    @DisplayName("Should delete zoo successfully")
    void shouldDeleteZooSuccessfully() {
        // Given
        Zoo zoo = new Zoo(null, "Manila Zoo", "Manila, Philippines", 
                         "A beautiful zoo in the heart of Manila", new ArrayList<>(), new ArrayList<>());
        Zoo createdZoo = zooService.createZoo(zoo);

        // When
        zooService.deleteZoo(createdZoo.id());

        // Then
        assertFalse(zooService.zooExists(createdZoo.id()));
        assertTrue(zooService.getAllZoos().isEmpty());
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existent zoo")
    void shouldThrowExceptionWhenDeletingNonExistentZoo() {
        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> zooService.deleteZoo(999L)
        );
        assertEquals("Zoo not found with id: 999", exception.getMessage());
    }

    @Test
    @DisplayName("Should return multiple zoos when they exist")
    void shouldReturnMultipleZoosWhenTheyExist() {
        // Given
        Zoo zoo1 = new Zoo(null, "Manila Zoo", "Manila, Philippines", 
                          "A beautiful zoo in the heart of Manila", new ArrayList<>(), new ArrayList<>());
        Zoo zoo2 = new Zoo(null, "Cebu Safari", "Cebu, Philippines", 
                          "World famous safari park", new ArrayList<>(), new ArrayList<>());

        // When
        zooService.createZoo(zoo1);
        zooService.createZoo(zoo2);
        List<Zoo> zoos = zooService.getAllZoos();

        // Then
        assertEquals(2, zoos.size());
        assertTrue(zoos.stream().anyMatch(z -> z.name().equals("Manila Zoo")));
        assertTrue(zoos.stream().anyMatch(z -> z.name().equals("Cebu Safari")));
    }

    @Test
    @DisplayName("Should check if zoo exists")
    void shouldCheckIfZooExists() {
        // Given
        Zoo zoo = new Zoo(null, "Manila Zoo", "Manila, Philippines", 
                         "A beautiful zoo in the heart of Manila", new ArrayList<>(), new ArrayList<>());
        Zoo createdZoo = zooService.createZoo(zoo);

        // When & Then
        assertTrue(zooService.zooExists(createdZoo.id()));
        assertFalse(zooService.zooExists(999L));
    }
} 