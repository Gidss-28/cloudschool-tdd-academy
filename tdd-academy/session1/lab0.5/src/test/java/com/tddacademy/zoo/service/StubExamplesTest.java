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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StubExamplesTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    private Animal simba;
    private Animal nala;
    private Animal timon;

    @BeforeEach
    void setUp() {
        simba = new Animal("Simba", "Lion", 180.5, LocalDate.of(2020, 5, 15), "Healthy");
        nala = new Animal("Nala", "Lion", 160.0, LocalDate.of(2020, 6, 20), "Healthy");
        timon = new Animal("Timon", "Meerkat", 2.5, LocalDate.of(2021, 3, 10), "Healthy");
    }

    @Test
    @DisplayName("Stub Example 1: Should find animals by species using stub")
    void shouldFindAnimalsBySpeciesUsingStub() {
        // Given - Create a stub that returns predefined data
        List<Animal> lions = Arrays.asList(simba, nala);
        when(animalRepository.findBySpecies("Lion")).thenReturn(lions);

        // When
        List<Animal> foundLions = animalService.getAnimalsBySpecies("Lion");

        // Then
        assertEquals(2, foundLions.size());
        assertTrue(foundLions.stream().allMatch(animal -> "Lion".equals(animal.getSpecies())));
    }

    @Test
    @DisplayName("Stub Example 2: Should return empty list for non-existent species")
    void shouldReturnEmptyListForNonExistentSpecies() {
        // Given - Stub returns empty list
        when(animalRepository.findBySpecies("Dragon")).thenReturn(Arrays.asList());

        // When
        List<Animal> foundAnimals = animalService.getAnimalsBySpecies("Dragon");

        // Then
        assertTrue(foundAnimals.isEmpty());
    }

    @Test
    @DisplayName("Stub Example 3: Should get animal count using stub")
    void shouldGetAnimalCountUsingStub() {
        // Given - Stub returns a fixed count
        when(animalRepository.count()).thenReturn(10);

        // When
        int count = animalService.getAnimalCount();

        // Then
        assertEquals(10, count);
    }

    @Test
    @DisplayName("Stub Example 4: Should calculate average weight with stub data")
    void shouldCalculateAverageWeightWithStubData() {
        // Given - Stub returns predefined animals
        List<Animal> animals = Arrays.asList(simba, nala, timon);
        when(animalRepository.findAll()).thenReturn(animals);

        // When
        double averageWeight = animalService.getAverageWeight();

        // Then
        // (180.5 + 160.0 + 2.5) / 3 = 114.33
        assertEquals(114.33, averageWeight, 0.01);
    }

    @Test
    @DisplayName("Stub Example 5: Should handle healthy animal check with stub")
    void shouldHandleHealthyAnimalCheckWithStub() {
        // Given - Stub returns a healthy animal
        simba.setId(1L);
        when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));

        // When
        boolean isHealthy = animalService.isAnimalHealthy(1L);

        // Then
        assertTrue(isHealthy);
    }

    @Test
    @DisplayName("Stub Example 6: Should handle sick animal check with stub")
    void shouldHandleSickAnimalCheckWithStub() {
        // Given - Stub returns a sick animal
        simba.setId(1L);
        simba.setHealthStatus("Sick");
        when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));

        // When
        boolean isHealthy = animalService.isAnimalHealthy(1L);

        // Then
        assertFalse(isHealthy);
    }

    @Test
    @DisplayName("Stub Example 7: Should create animal with stub response")
    void shouldCreateAnimalWithStubResponse() {
        // Given - Stub returns the saved animal with ID
        simba.setId(1L);
        when(animalRepository.save(any(Animal.class))).thenReturn(simba);

        // When
        Animal createdAnimal = animalService.createAnimal(simba);

        // Then
        assertNotNull(createdAnimal.getId());
        assertEquals("Simba", createdAnimal.getName());
    }

    @Test
    @DisplayName("Stub Example 8: Should delete animal successfully with stub")
    void shouldDeleteAnimalSuccessfullyWithStub() {
        // Given - Stub confirms animal exists
        when(animalRepository.existsById(1L)).thenReturn(true);
        doNothing().when(animalRepository).deleteById(1L);

        // When
        boolean deleted = animalService.deleteAnimal(1L);

        // Then
        assertTrue(deleted);
    }

    @Test
    @DisplayName("Stub Example 9: Should fail to delete non-existent animal with stub")
    void shouldFailToDeleteNonExistentAnimalWithStub() {
        // Given - Stub confirms animal doesn't exist
        when(animalRepository.existsById(999L)).thenReturn(false);

        // When
        boolean deleted = animalService.deleteAnimal(999L);

        // Then
        assertFalse(deleted);
    }

    @Test
    @DisplayName("Stub Example 10: Should get all animals with stub data")
    void shouldGetAllAnimalsWithStubData() {
        // Given - Stub returns predefined list
        List<Animal> allAnimals = Arrays.asList(simba, nala, timon);
        when(animalRepository.findAll()).thenReturn(allAnimals);

        // When
        List<Animal> result = animalService.getAllAnimals();

        // Then
        assertEquals(3, result.size());
        assertEquals("Simba", result.get(0).getName());
        assertEquals("Nala", result.get(1).getName());
        assertEquals("Timon", result.get(2).getName());
    }

    @Test
    @DisplayName("Stub Example 11: Should handle empty repository with stub")
    void shouldHandleEmptyRepositoryWithStub() {
        // Given - Stub returns empty list
        when(animalRepository.findAll()).thenReturn(Arrays.asList());

        // When
        List<Animal> result = animalService.getAllAnimals();

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Stub Example 12: Should calculate zero average for empty repository")
    void shouldCalculateZeroAverageForEmptyRepository() {
        // Given - Stub returns empty list
        when(animalRepository.findAll()).thenReturn(Arrays.asList());

        // When
        double averageWeight = animalService.getAverageWeight();

        // Then
        assertEquals(0.0, averageWeight, 0.01);
    }
} 