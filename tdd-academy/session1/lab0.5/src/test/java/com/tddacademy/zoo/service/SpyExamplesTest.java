package com.tddacademy.zoo.service;

import com.tddacademy.zoo.model.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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
class SpyExamplesTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private AnimalService animalService;

    @Spy
    private NotificationService notificationService;

    @InjectMocks
    private ZooManager zooManager;

    private Animal simba;
    private Animal nala;

    @BeforeEach
    void setUp() {
        simba = new Animal("Simba", "Lion", 180.5, LocalDate.of(2020, 5, 15), "Healthy");
        nala = new Animal("Nala", "Lion", 160.0, LocalDate.of(2020, 6, 20), "Healthy");
    }

    @Test
    @DisplayName("Spy Example 1: Should verify notification was sent when adding animal")
    void shouldVerifyNotificationWasSentWhenAddingAnimal() {
        // Given
        simba.setId(1L);
        when(animalService.createAnimal(any(Animal.class))).thenReturn(simba);

        // When
        Animal addedAnimal = zooManager.addNewAnimal(simba);

        // Then
        assertNotNull(addedAnimal);
        verify(notificationService, times(1)).sendEmail(
            eq("staff@zoo.com"),
            eq("New Animal Added"),
            contains("Simba")
        );
    }

    @Test
    @DisplayName("Spy Example 2: Should verify SMS was sent when removing animal")
    void shouldVerifySMSWasSentWhenRemovingAnimal() {
        // Given
        simba.setId(1L);
        when(animalService.getAnimalById(1L)).thenReturn(Optional.of(simba));
        when(animalService.deleteAnimal(1L)).thenReturn(true);

        // When
        boolean removed = zooManager.removeAnimal(1L);

        // Then
        assertTrue(removed);
        verify(notificationService, times(1)).sendSMS(
            eq("+1234567890"),
            contains("Simba")
        );
    }

    @Test
    @DisplayName("Spy Example 3: Should verify email was sent for unhealthy animal")
    void shouldVerifyEmailWasSentForUnhealthyAnimal() {
        // Given
        simba.setId(1L);
        simba.setHealthStatus("Sick");
        when(animalService.isAnimalHealthy(1L)).thenReturn(false);

        // When
        zooManager.checkAnimalHealth(1L);

        // Then
        verify(notificationService, times(1)).sendEmail(
            eq("vet@zoo.com"),
            eq("Animal Health Alert"),
            contains("1")
        );
    }

    @Test
    @DisplayName("Spy Example 4: Should not send notification for healthy animal")
    void shouldNotSendNotificationForHealthyAnimal() {
        // Given
        simba.setId(1L);
        simba.setHealthStatus("Healthy");
        when(animalService.isAnimalHealthy(1L)).thenReturn(true);

        // When
        zooManager.checkAnimalHealth(1L);

        // Then
        verify(notificationService, never()).sendEmail(any(), any(), any());
    }

    @Test
    @DisplayName("Spy Example 6: Should verify email service availability check")
    void shouldVerifyEmailServiceAvailabilityCheck() {
        // Given
        when(notificationService.isEmailServiceAvailable()).thenReturn(true);

        // When
        boolean isAvailable = notificationService.isEmailServiceAvailable();

        // Then
        assertTrue(isAvailable);
        verify(notificationService, times(1)).isEmailServiceAvailable();
    }

    @Test
    @DisplayName("Spy Example 7: Should verify multiple notifications for multiple animals")
    void shouldVerifyMultipleNotificationsForMultipleAnimals() {
        // Given
        simba.setId(1L);
        nala.setId(2L);
        when(animalService.createAnimal(any(Animal.class))).thenReturn(simba).thenReturn(nala);

        // When
        zooManager.addNewAnimal(simba);
        zooManager.addNewAnimal(nala);

        // Then
        verify(notificationService, times(2)).sendEmail(
            eq("staff@zoo.com"),
            eq("New Animal Added"),
            any()
        );
    }

    @Test
    @DisplayName("Spy Example 8: Should verify notification parameters")
    void shouldVerifyNotificationParameters() {
        // Given
        simba.setId(1L);
        when(animalService.createAnimal(any(Animal.class))).thenReturn(simba);

        // When
        zooManager.addNewAnimal(simba);

        // Then
        verify(notificationService).sendEmail(
            "staff@zoo.com",
            "New Animal Added",
            "New animal Simba has been added to the zoo."
        );
    }

    @Test
    @DisplayName("Spy Example 9: Should verify no notifications for failed operations")
    void shouldVerifyNoNotificationsForFailedOperations() {
        // Given
        when(animalService.getAnimalById(999L)).thenReturn(Optional.empty());

        // When
        boolean removed = zooManager.removeAnimal(999L);

        // Then
        assertFalse(removed);
        verify(notificationService, never()).sendSMS(any(), any());
    }

    @Test
    @DisplayName("Spy Example 10: Should verify notification service interaction")
    void shouldVerifyNotificationServiceInteraction() {
        // Given
        simba.setId(1L);
        when(animalService.createAnimal(any(Animal.class))).thenReturn(simba);

        // When
        Animal result = zooManager.addNewAnimal(simba);

        // Then
        assertNotNull(result);
        verify(notificationService, atLeastOnce()).sendEmail(any(), any(), any());
    }
} 