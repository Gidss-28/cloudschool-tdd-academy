package com.tddacademy.zoo.service;

import com.tddacademy.zoo.model.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MockExamplesTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    private Animal simba;
    private Animal nala;

    @BeforeEach
    void setUp() {
        simba = new Animal("Simba", "Lion", 180.5, LocalDate.of(2020, 5, 15), "Healthy");
        nala = new Animal("Nala", "Lion", 160.0, LocalDate.of(2020, 6, 20), "Healthy");
    }

    @Test
    @DisplayName("Mock Example 1: Should create animal successfully")
    void shouldCreateAnimalSuccessfully() {
        // Given - Setup the mock behavior
        simba.setId(1L);
        when(animalRepository.save(any(Animal.class))).thenReturn(simba);

        // When - Call the method under test
        Animal createdAnimal = animalService.createAnimal(simba);

        // Then - Verify the result and mock interaction
        assertNotNull(createdAnimal);
        assertEquals("Simba", createdAnimal.getName());
        verify(animalRepository, times(1)).save(simba);
    }

    @Test
    @DisplayName("Mock Example 2: Should find animal by id when exists")
    void shouldFindAnimalByIdWhenExists() {
        // Given
        simba.setId(1L);
        when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));

        // When
        Optional<Animal> foundAnimal = animalService.getAnimalById(1L);

        // Then
        assertTrue(foundAnimal.isPresent());
        assertEquals("Simba", foundAnimal.get().getName());
    }

    @Test
    @DisplayName("Mock Example 3: Should return empty when animal not found")
    void shouldReturnEmptyWhenAnimalNotFound() {
        // Given
        when(animalRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<Animal> foundAnimal = animalService.getAnimalById(999L);

        // Then
        assertTrue(foundAnimal.isEmpty());
    }

    @Test
    @DisplayName("Mock Example 4: Should get all animals")
    void shouldGetAllAnimals() {
        // Given
        List<Animal> animals = Arrays.asList(simba, nala);
        when(animalRepository.findAll()).thenReturn(animals);

        // When
        List<Animal> allAnimals = animalService.getAllAnimals();

        // Then
        assertEquals(2, allAnimals.size());
        assertEquals("Simba", allAnimals.get(0).getName());
        assertEquals("Nala", allAnimals.get(1).getName());
    }

    @Test
    @DisplayName("Mock Example 5: Should calculate average weight")
    void shouldCalculateAverageWeight() {
        // Given
        List<Animal> animals = Arrays.asList(simba, nala);
        when(animalRepository.findAll()).thenReturn(animals);

        // When
        double averageWeight = animalService.getAverageWeight();

        // Then
        assertEquals(170.25, averageWeight, 0.01);
    }

    @Test
    @DisplayName("Mock Example 6: Should return zero average weight for empty list")
    void shouldReturnZeroAverageWeightForEmptyList() {
        // Given
        when(animalRepository.findAll()).thenReturn(Arrays.asList());

        // When
        double averageWeight = animalService.getAverageWeight();

        // Then
        assertEquals(0.0, averageWeight, 0.01);
    }

    @Test
    @DisplayName("Mock Example 7: Should delete animal when exists")
    void shouldDeleteAnimalWhenExists() {
        // Given
        when(animalRepository.existsById(1L)).thenReturn(true);
        doNothing().when(animalRepository).deleteById(1L);

        // When
        boolean deleted = animalService.deleteAnimal(1L);

        // Then
        assertTrue(deleted);
        verify(animalRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Mock Example 8: Should return false when deleting non-existent animal")
    void shouldReturnFalseWhenDeletingNonExistentAnimal() {
        // Given
        when(animalRepository.existsById(999L)).thenReturn(false);

        // When
        boolean deleted = animalService.deleteAnimal(999L);

        // Then
        assertFalse(deleted);
        verify(animalRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("Mock Example 9: Should check if animal is healthy")
    void shouldCheckIfAnimalIsHealthy() {
        // Given
        simba.setId(1L);
        when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));

        // When
        boolean isHealthy = animalService.isAnimalHealthy(1L);

        // Then
        assertTrue(isHealthy);
    }

    @Test
    @DisplayName("Mock Example 10: Should return false for unhealthy animal")
    void shouldReturnFalseForUnhealthyAnimal() {
        // Given
        simba.setId(1L);
        simba.setHealthStatus("Sick");
        when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));

        // When
        boolean isHealthy = animalService.isAnimalHealthy(1L);

        // Then
        assertFalse(isHealthy);
    }

    @Test
    @DisplayName("Mock Example 11: Should return false for non-existent animal")
    void shouldReturnFalseForNonExistentAnimal() {
        // Given
        when(animalRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        boolean isHealthy = animalService.isAnimalHealthy(999L);

        // Then
        assertFalse(isHealthy);
    }

    @Test
    @DisplayName("Mock Example 12: Should get animal count")
    void shouldGetAnimalCount() {
        // Given
        when(animalRepository.count()).thenReturn(5);

        // When
        int count = animalService.getAnimalCount();

        // Then
        assertEquals(5, count);
    }
} 