# Lab 0.5 Summary: Test Doubles with Mockito

## Test Overview

Lab 0.5 contains **46 tests** across four test classes, with **34 completed examples** and **12 TODO exercises** for students to complete.

## Test Breakdown

### MockExamplesTest (12 tests) - ✅ Completed
**Mock Examples - Behavior Verification:**
1. `shouldCreateAnimalSuccessfully()` - Basic mock setup and verification
2. `shouldFindAnimalByIdWhenExists()` - Mock with return value
3. `shouldReturnEmptyWhenAnimalNotFound()` - Mock with empty response
4. `shouldGetAllAnimals()` - Mock with list return
5. `shouldCalculateAverageWeight()` - Mock with calculation
6. `shouldReturnZeroAverageWeightForEmptyList()` - Mock with empty list
7. `shouldDeleteAnimalWhenExists()` - Mock with void method
8. `shouldReturnFalseWhenDeletingNonExistentAnimal()` - Mock with boolean
9. `shouldCheckIfAnimalIsHealthy()` - Mock with health check
10. `shouldReturnFalseForUnhealthyAnimal()` - Mock with sick animal
11. `shouldReturnFalseForNonExistentAnimal()` - Mock with not found
12. `shouldGetAnimalCount()` - Mock with count

### StubExamplesTest (12 tests) - ✅ Completed
**Stub Examples - Predefined Responses:**
1. `shouldFindAnimalsBySpeciesUsingStub()` - Stub with species data
2. `shouldReturnEmptyListForNonExistentSpecies()` - Stub with empty list
3. `shouldGetAnimalCountUsingStub()` - Stub with fixed count
4. `shouldCalculateAverageWeightWithStubData()` - Stub with multiple animals
5. `shouldHandleHealthyAnimalCheckWithStub()` - Stub with healthy animal
6. `shouldHandleSickAnimalCheckWithStub()` - Stub with sick animal
7. `shouldCreateAnimalWithStubResponse()` - Stub with saved animal
8. `shouldDeleteAnimalSuccessfullyWithStub()` - Stub with existence check
9. `shouldFailToDeleteNonExistentAnimalWithStub()` - Stub with non-existence
10. `shouldGetAllAnimalsWithStubData()` - Stub with multiple animals
11. `shouldHandleEmptyRepositoryWithStub()` - Stub with empty repository
12. `shouldCalculateZeroAverageForEmptyRepository()` - Stub with zero average

### SpyExamplesTest (10 tests) - ✅ Completed
**Spy Examples - Real Object Monitoring:**
1. `shouldVerifyNotificationWasSentWhenAddingAnimal()` - Spy on email sending
2. `shouldVerifySMSWasSentWhenRemovingAnimal()` - Spy on SMS sending
3. `shouldVerifyEmailWasSentForUnhealthyAnimal()` - Spy on health alerts
4. `shouldNotSendNotificationForHealthyAnimal()` - Spy on no notification
5. `shouldVerifyNotificationCount()` - Spy on count tracking
6. `shouldVerifyEmailServiceAvailabilityCheck()` - Spy on availability
7. `shouldVerifyMultipleNotificationsForMultipleAnimals()` - Spy on multiple calls
8. `shouldVerifyNotificationParameters()` - Spy on exact parameters
9. `shouldVerifyNoNotificationsForFailedOperations()` - Spy on failure
10. `shouldVerifyNotificationServiceInteraction()` - Spy on interaction

### TodoExercisesTest (12 tests) - 📝 TODO Exercises
**Student Exercises:**
- **Mock Exercises (3)**: Basic mock usage
- **Stub Exercises (3)**: Predefined data responses
- **Spy Exercises (3)**: Real object monitoring
- **Advanced Exercises (3)**: Complex scenarios

## TODO Exercise Solutions

### Mock Exercises

#### 1. Find Animal by Species
```java
@Test
@DisplayName("TODO: Mock Exercise 1 - Should find animal by species")
void shouldFindAnimalBySpecies() {
    // Given
    when(animalRepository.findBySpecies("Lion")).thenReturn(Arrays.asList(simba, nala));
    
    // When
    List<Animal> lions = animalService.getAnimalsBySpecies("Lion");
    
    // Then
    assertEquals(2, lions.size());
    assertTrue(lions.stream().allMatch(animal -> "Lion".equals(animal.getSpecies())));
}
```

#### 2. Handle Animal Not Found
```java
@Test
@DisplayName("TODO: Mock Exercise 2 - Should handle animal not found")
void shouldHandleAnimalNotFound() {
    // Given
    when(animalRepository.findById(999L)).thenReturn(Optional.empty());
    
    // When
    Optional<Animal> result = animalService.getAnimalById(999L);
    
    // Then
    assertTrue(result.isEmpty());
}
```

#### 3. Verify Repository Save
```java
@Test
@DisplayName("TODO: Mock Exercise 3 - Should verify repository save was called")
void shouldVerifyRepositorySaveWasCalled() {
    // Given
    simba.setId(1L);
    when(animalRepository.save(any(Animal.class))).thenReturn(simba);
    
    // When
    animalService.createAnimal(simba);
    
    // Then
    verify(animalRepository, times(1)).save(simba);
}
```

### Stub Exercises

#### 1. Calculate Average Weight
```java
@Test
@DisplayName("TODO: Stub Exercise 1 - Should calculate average weight with stub data")
void shouldCalculateAverageWeightWithStubData() {
    // Given
    List<Animal> animals = Arrays.asList(simba, nala, timon);
    when(animalRepository.findAll()).thenReturn(animals);
    
    // When
    double averageWeight = animalService.getAverageWeight();
    
    // Then
    assertEquals(114.33, averageWeight, 0.01);
}
```

#### 2. Handle Empty Repository
```java
@Test
@DisplayName("TODO: Stub Exercise 2 - Should handle empty repository with stub")
void shouldHandleEmptyRepositoryWithStub() {
    // Given
    when(animalRepository.findAll()).thenReturn(Arrays.asList());
    
    // When
    double averageWeight = animalService.getAverageWeight();
    
    // Then
    assertEquals(0.0, averageWeight, 0.01);
}
```

#### 3. Get Animal Count
```java
@Test
@DisplayName("TODO: Stub Exercise 3 - Should get animal count with stub")
void shouldGetAnimalCountWithStub() {
    // Given
    when(animalRepository.count()).thenReturn(15);
    
    // When
    int count = animalService.getAnimalCount();
    
    // Then
    assertEquals(15, count);
}
```

### Spy Exercises

#### 1. Verify Email Notification
```java
@Test
@DisplayName("TODO: Spy Exercise 1 - Should verify email notification for new animal")
void shouldVerifyEmailNotificationForNewAnimal() {
    // Given
    simba.setId(1L);
    when(animalRepository.save(any(Animal.class))).thenReturn(simba);
    
    // When
    zooManager.addNewAnimal(simba);
    
    // Then
    verify(notificationService, times(1)).sendEmail(
        eq("staff@zoo.com"),
        eq("New Animal Added"),
        contains("Simba")
    );
}
```

#### 2. Verify SMS Notification
```java
@Test
@DisplayName("TODO: Spy Exercise 2 - Should verify SMS notification for animal removal")
void shouldVerifySMSNotificationForAnimalRemoval() {
    // Given
    simba.setId(1L);
    when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));
    when(animalRepository.existsById(1L)).thenReturn(true);
    doNothing().when(animalRepository).deleteById(1L);
    
    // When
    zooManager.removeAnimal(1L);
    
    // Then
    verify(notificationService, times(1)).sendSMS(
        eq("+1234567890"),
        contains("Simba")
    );
}
```

#### 3. Verify No Notification
```java
@Test
@DisplayName("TODO: Spy Exercise 3 - Should verify no notification for healthy animal")
void shouldVerifyNoNotificationForHealthyAnimal() {
    // Given
    simba.setId(1L);
    simba.setHealthStatus("Healthy");
    when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));
    
    // When
    zooManager.checkAnimalHealth(1L);
    
    // Then
    verify(notificationService, never()).sendEmail(any(), any(), any());
}
```

### Advanced Exercises

#### 1. Multiple Repository Calls
```java
@Test
@DisplayName("TODO: Advanced Exercise 1 - Should verify multiple repository calls")
void shouldVerifyMultipleRepositoryCalls() {
    // Given
    List<Animal> animals = Arrays.asList(simba, nala);
    when(animalRepository.findAll()).thenReturn(animals);
    
    // When
    double averageWeight = animalService.getAverageWeight();
    
    // Then
    verify(animalRepository, times(1)).findAll();
    assertEquals(170.25, averageWeight, 0.01);
}
```

#### 2. Exact Parameter Matching
```java
@Test
@DisplayName("TODO: Advanced Exercise 2 - Should verify notification parameters exactly")
void shouldVerifyNotificationParametersExactly() {
    // Given
    simba.setId(1L);
    when(animalRepository.save(any(Animal.class))).thenReturn(simba);
    
    // When
    zooManager.addNewAnimal(simba);
    
    // Then
    verify(notificationService).sendEmail(
        "staff@zoo.com",
        "New Animal Added",
        "New animal Simba has been added to the zoo."
    );
}
```

#### 3. Complex Scenario
```java
@Test
@DisplayName("TODO: Advanced Exercise 3 - Should handle complex scenario with multiple mocks")
void shouldHandleComplexScenarioWithMultipleMocks() {
    // Given
    simba.setId(1L);
    simba.setHealthStatus("Sick");
    when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));
    
    // When
    zooManager.checkAnimalHealth(1L);
    
    // Then
    verify(notificationService, times(1)).sendEmail(
        eq("vet@zoo.com"),
        eq("Animal Health Alert"),
        contains("1")
    );
    verify(animalRepository, times(1)).findById(1L);
}
```

## Key Learning Points

### Mock Concepts
- **Behavior Verification**: Ensure methods are called correctly
- **Return Value Configuration**: Control what mocks return
- **Argument Matching**: Use any(), eq(), contains() for flexible matching
- **Call Verification**: Verify method calls with times(), never(), atLeastOnce()

### Stub Concepts
- **Predefined Data**: Return specific values for testing scenarios
- **Edge Cases**: Handle empty lists, null values, error conditions
- **Data Flow**: Test how data moves through your application
- **Scenario Testing**: Test different business scenarios

### Spy Concepts
- **Real Object Monitoring**: Track calls to real objects
- **Interaction Verification**: Ensure correct interactions happen
- **Parameter Validation**: Verify exact parameters passed
- **Integration Testing**: Test component interactions

## Common Patterns

### Mock Pattern
```java
@Mock
private Dependency dependency;

@InjectMocks
private ClassUnderTest classUnderTest;

@Test
void shouldDoSomething() {
    // Given
    when(dependency.method(any())).thenReturn(expectedValue);
    
    // When
    Result result = classUnderTest.method();
    
    // Then
    verify(dependency, times(1)).method(any());
    assertEquals(expectedValue, result);
}
```

### Stub Pattern
```java
@Test
void shouldHandleScenario() {
    // Given
    when(repository.findAll()).thenReturn(Arrays.asList(item1, item2));
    
    // When
    double average = service.calculateAverage();
    
    // Then
    assertEquals(expectedAverage, average, 0.01);
}
```

### Spy Pattern
```java
@Spy
private RealService realService;

@Test
void shouldInteractCorrectly() {
    // Given
    Input input = new Input();
    
    // When
    service.process(input);
    
    // Then
    verify(realService).method(eq(expectedParam));
}
```

## Best Practices

### When to Use Each Type
- **Mocks**: When you need to verify behavior and interactions
- **Stubs**: When you need predefined responses for testing scenarios
- **Spies**: When you want to monitor real object behavior

### Test Structure
1. **Arrange**: Set up mocks, stubs, and test data
2. **Act**: Call the method under test
3. **Assert**: Verify results and interactions

### Verification Guidelines
- Verify only what matters for the test
- Use appropriate matchers (any(), eq(), contains())
- Check call counts when relevant
- Verify interactions in complex scenarios

## Next Steps

After completing Lab 0.5, students will be ready for:
- **Lab 1**: Basic unit testing with JUnit 5
- **Lab 1.5**: MockMvc testing basics
- **Lab 2**: Controller testing with MockMvc
- **Lab 3**: JPA persistence testing 