package com.tddacademy.zoo.service;

import com.tddacademy.zoo.model.Zoo;
import com.tddacademy.zoo.repository.ZooRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ZooServiceTest {

    @Mock
    private ZooRepository zooRepository;

    @InjectMocks
    private ZooService zooService;

    private Zoo manilaZoo;
    private Zoo cebuSafari;

    @BeforeEach
    void setUp() {
        manilaZoo = new Zoo("Manila Zoo", "Manila, Philippines", "A beautiful zoo in the heart of Manila");
        cebuSafari = new Zoo("Cebu Safari", "Cebu, Philippines", "World famous safari park");
    }

    @Test
    @DisplayName("Should get all zoos")
    void shouldGetAllZoos() {
        // Given
        List<Zoo> expectedZoos = Arrays.asList(manilaZoo, cebuSafari);
        when(zooRepository.findAll()).thenReturn(expectedZoos);

        // When
        List<Zoo> actualZoos = zooService.getAllZoos();

        // Then
        assertEquals(2, actualZoos.size());
        assertEquals("Manila Zoo", actualZoos.get(0).getName());
        assertEquals("Cebu Safari", actualZoos.get(1).getName());
    }

    @Test
    @DisplayName("Should get zoo by id when exists")
    void shouldGetZooByIdWhenExists() {
        // Given
        Long zooId = 1L;
        manilaZoo.setId(zooId);
        when(zooRepository.findById(zooId)).thenReturn(Optional.of(manilaZoo));

        // When
        Optional<Zoo> foundZoo = zooService.getZooById(zooId);

        // Then
        assertTrue(foundZoo.isPresent());
        assertEquals("Manila Zoo", foundZoo.get().getName());
    }

    @Test
    @DisplayName("Should return empty when zoo not found")
    void shouldReturnEmptyWhenZooNotFound() {
        // Given
        Long zooId = 999L;
        when(zooRepository.findById(zooId)).thenReturn(Optional.empty());

        // When
        Optional<Zoo> foundZoo = zooService.getZooById(zooId);

        // Then
        assertTrue(foundZoo.isEmpty());
    }

    @Test
    @DisplayName("Should create zoo successfully")
    void shouldCreateZooSuccessfully() {
        // Given
        manilaZoo.setId(1L);
        when(zooRepository.save(any(Zoo.class))).thenReturn(manilaZoo);

        // When
        Zoo createdZoo = zooService.createZoo(manilaZoo);

        // Then
        assertNotNull(createdZoo.getId());
        assertEquals("Manila Zoo", createdZoo.getName());
        verify(zooRepository, times(1)).save(manilaZoo);
    }

    @Test
    @DisplayName("Should update zoo when exists")
    void shouldUpdateZooWhenExists() {
        // Your code here:
        Long zooId = 1L;
        manilaZoo.setId(zooId);
        Zoo updatedZoo = new Zoo("Updated Manila Zoo", "Updated Location", "Updated description");

        when(zooRepository.findById(zooId)).thenReturn(Optional.of(manilaZoo));
        when(zooRepository.save(any(Zoo.class))).thenReturn(updatedZoo);

        Zoo result = zooService.updateZoo(zooId, updatedZoo);

        assertEquals("Updated Manila Zoo", result.getName());
        verify(zooRepository, times(1)).save(any(Zoo.class));
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent zoo")
    void shouldThrowExceptionWhenUpdatingNonExistentZoo() {
        // Your code here:
        Long zooId = 999L;
        Zoo updatedZoo = new Zoo("Updated Zoo", "Updated Location", "Updated description");

        when(zooRepository.findById(zooId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> zooService.updateZoo(zooId, updatedZoo)
        );
        assertTrue(exception.getMessage().contains("Zoo not found with id: 999"));
    }

    @Test
    @DisplayName("Should delete zoo when exists")
    void shouldDeleteZooWhenExists() {
        // Your code here:
        Long zooId = 1L;
        when(zooRepository.existsById(zooId)).thenReturn(true);

        zooService.deleteZoo(zooId);

        verify(zooRepository, times(1)).deleteById(zooId);
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existent zoo")
    void shouldThrowExceptionWhenDeletingNonExistentZoo() {
        // Your code here:
        Long zooId = 999L;
        when(zooRepository.existsById(zooId)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> zooService.deleteZoo(zooId)
        );
        assertTrue(exception.getMessage().contains("Zoo not found with id: 999"));
    }

    @Test
    @DisplayName("Should find zoos by name")
    void shouldFindZoosByName() {
        // Given
        List<Zoo> expectedZoos = Arrays.asList(manilaZoo);
        when(zooRepository.findByNameContainingIgnoreCase("Manila")).thenReturn(expectedZoos);

        // When
        List<Zoo> foundZoos = zooService.findZoosByName("Manila");

        // Then
        assertEquals(1, foundZoos.size());
        assertEquals("Manila Zoo", foundZoos.get(0).getName());
    }

    @Test
    @DisplayName("Should check if zoo exists")
    void shouldCheckIfZooExists() {
        // Given
        Long zooId = 1L;
        when(zooRepository.existsById(zooId)).thenReturn(true);
        when(zooRepository.existsById(999L)).thenReturn(false);

        // When & Then
        assertTrue(zooService.zooExists(zooId));
        assertFalse(zooService.zooExists(999L));
    }
}