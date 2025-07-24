package com.tddacademy.zoo.repository;

import com.tddacademy.zoo.model.Zoo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ZooRepositoryTest {

    @Autowired
    private ZooRepository zooRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Zoo manilaZoo;
    private Zoo cebuSafari;

    @BeforeEach
    void setUp() {
        // Create test data
        manilaZoo = new Zoo("Manila Zoo", "Manila, Philippines", "A beautiful zoo in the heart of Manila");
        cebuSafari = new Zoo("Cebu Safari", "Cebu, Philippines", "World famous safari park");
    }

    @Test
    @DisplayName("Should save a zoo successfully")
    void shouldSaveZooSuccessfully() {
        // When
        Zoo savedZoo = zooRepository.save(manilaZoo);

        // Then
        assertNotNull(savedZoo.getId());
        assertEquals("Manila Zoo", savedZoo.getName());
        assertEquals("Manila, Philippines", savedZoo.getLocation());
    }

    @Test
    @DisplayName("Should find zoo by id")
    void shouldFindZooById() {
        // Given
        Zoo savedZoo = zooRepository.save(manilaZoo);
        Long zooId = savedZoo.getId();

        // When
        Optional<Zoo> foundZoo = zooRepository.findById(zooId);

        // Then
        assertTrue(foundZoo.isPresent());
        assertEquals("Manila Zoo", foundZoo.get().getName());
    }

    @Test
    @DisplayName("Should find all zoos")
    void shouldFindAllZoos() {
        // Given
        zooRepository.save(manilaZoo);
        zooRepository.save(cebuSafari);

        // When
        List<Zoo> allZoos = zooRepository.findAll();

        // Then
        assertEquals(2, allZoos.size());
        assertTrue(allZoos.stream().anyMatch(zoo -> zoo.getName().equals("Manila Zoo")));
        assertTrue(allZoos.stream().anyMatch(zoo -> zoo.getName().equals("Cebu Safari")));
    }

    @Test
    @DisplayName("Should find zoos by name containing")
    void shouldFindZoosByNameContaining() {
        // Your code here:
        zooRepository.save(manilaZoo);
        zooRepository.save(cebuSafari);

        List<Zoo> manilaZoos = zooRepository.findByNameContainingIgnoreCase("Manila");

        assertEquals(1, manilaZoos.size());
        assertEquals("Manila Zoo", manilaZoos.get(0).getName());
    }

    @Test
    @DisplayName("Should find zoos by location containing")
    void shouldFindZoosByLocationContaining() {
        // Your code here:
        zooRepository.save(manilaZoo);
        zooRepository.save(cebuSafari);

        List<Zoo> philippineZoos = zooRepository.findByLocationContainingIgnoreCase("Philippines");

        assertEquals(2, philippineZoos.size());
        assertTrue(philippineZoos.stream().anyMatch(zoo -> zoo.getName().equals("Manila Zoo")));
        assertTrue(philippineZoos.stream().anyMatch(zoo -> zoo.getName().equals("Cebu Safari")));
    }

    @Test
    @DisplayName("Should check if zoo exists by id")
    void shouldCheckIfZooExistsById() {
        // Your code here:
        Zoo savedZoo = zooRepository.save(manilaZoo);
        Long savedId = savedZoo.getId();

        assertTrue(zooRepository.existsById(savedId));
        assertFalse(zooRepository.existsById(999L));
    }

    @Test
    @DisplayName("Should delete zoo by id")
    void shouldDeleteZooById() {
        // Your code here:
        Zoo savedZoo = zooRepository.save(manilaZoo);
        Long savedId = savedZoo.getId();

        zooRepository.deleteById(savedId);
        Optional<Zoo> deletedZoo = zooRepository.findById(savedId);

        assertTrue(deletedZoo.isEmpty());
    }
}