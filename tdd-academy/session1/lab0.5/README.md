# Lab 0.5: Test Doubles with Mockito

## Overview

Lab 0.5 introduces **Test Doubles** using **Mockito**, a powerful Java testing framework. You'll learn about different types of test doubles: **Mocks**, **Stubs**, and **Spies**, and how to use them effectively in unit testing.

## Learning Objectives

By the end of this lab, you will be able to:

- ✅ **Understand Test Doubles**: Know when and why to use different types
- ✅ **Use Mocks**: Create and configure mocks for behavior verification
- ✅ **Use Stubs**: Provide predefined responses for method calls
- ✅ **Use Spies**: Monitor real object interactions
- ✅ **Write Clean Tests**: Create readable and maintainable test code
- ✅ **Verify Interactions**: Ensure methods are called correctly

## Prerequisites

- Java 17 or higher
- Gradle 7.0 or higher
- Basic understanding of JUnit 5

## What are Test Doubles?

Test doubles are objects that replace real dependencies in tests. They help you:

- **Isolate the unit under test**
- **Control test data and behavior**
- **Verify interactions between components**
- **Speed up test execution**

## Types of Test Doubles

### 1. **Mock** 🎭
- **Purpose**: Verify behavior and interactions
- **Use when**: You want to ensure methods are called correctly
- **Key features**: Behavior verification, interaction tracking

### 2. **Stub** 📋
- **Purpose**: Provide predefined responses
- **Use when**: You need specific data for your test
- **Key features**: Return values, no behavior verification

### 3. **Spy** 👁️
- **Purpose**: Monitor real object interactions
- **Use when**: You want to track calls to a real object
- **Key features**: Real behavior + verification capabilities

## Project Structure

```
lab0.5/
├── src/
│   ├── main/
│   │   └── java/com/tddacademy/zoo/
│   │       ├── model/          # Simple Animal model
│   │       └── service/        # Service classes for testing
│   └── test/
│       └── java/com/tddacademy/zoo/
│           └── service/        # Test examples and exercises
├── build.gradle
└── README.md
```

## Key Mockito Concepts

### Annotations
- **@Mock**: Creates a mock object
- **@Spy**: Creates a spy object
- **@InjectMocks**: Injects mocks into the class under test
- **@ExtendWith(MockitoExtension.class)**: Enables Mockito support

### Common Methods
- **when()**: Define mock behavior
- **verify()**: Verify method calls
- **times()**: Specify call count
- **any()**: Match any argument
- **eq()**: Match exact argument

## Getting Started

### 1. Run Tests
```bash
./gradlew test
```

### 2. Run Specific Test Class
```bash
./gradlew test --tests MockExamplesTest
```

### 3. Run TODO Exercises
```bash
./gradlew test --tests TodoExercisesTest
```

## Test Examples

### Mock Examples (12 tests)
- Basic mock setup and verification
- Method call verification
- Argument matching
- Return value configuration

### Stub Examples (12 tests)
- Predefined data responses
- Empty list handling
- Fixed return values
- Edge case scenarios

### Spy Examples (10 tests)
- Real object monitoring
- Interaction verification
- Parameter validation
- Call count tracking

## TODO Exercises

### Mock Exercises (3 exercises)
1. **Find animal by species** - Mock repository method
2. **Handle animal not found** - Mock empty response
3. **Verify repository save** - Verify method calls

### Stub Exercises (3 exercises)
1. **Calculate average weight** - Use stub data
2. **Handle empty repository** - Stub empty response
3. **Get animal count** - Stub fixed count

### Spy Exercises (3 exercises)
1. **Verify email notification** - Spy on notification service
2. **Verify SMS notification** - Spy on SMS sending
3. **Verify no notification** - Spy on healthy animal

### Advanced Exercises (3 exercises)
1. **Multiple repository calls** - Complex verification
2. **Exact parameter matching** - Precise verification
3. **Complex scenarios** - Multiple mocks and spies

## Sample Code

### Basic Mock Setup
```java
@Mock
private AnimalRepository animalRepository;

@InjectMocks
private AnimalService animalService;

@Test
void shouldCreateAnimal() {
    // Given
    Animal animal = new Animal("Simba", "Lion", 180.5, ...);
    when(animalRepository.save(any(Animal.class))).thenReturn(animal);
    
    // When
    Animal result = animalService.createAnimal(animal);
    
    // Then
    assertNotNull(result);
    verify(animalRepository, times(1)).save(animal);
}
```

### Stub with Predefined Data
```java
@Test
void shouldCalculateAverageWeight() {
    // Given
    List<Animal> animals = Arrays.asList(simba, nala);
    when(animalRepository.findAll()).thenReturn(animals);
    
    // When
    double average = animalService.getAverageWeight();
    
    // Then
    assertEquals(170.25, average, 0.01);
}
```

### Spy for Interaction Verification
```java
@Spy
private NotificationService notificationService;

@Test
void shouldSendNotification() {
    // Given
    Animal animal = new Animal("Simba", ...);
    
    // When
    zooManager.addNewAnimal(animal);
    
    // Then
    verify(notificationService).sendEmail(
        eq("staff@zoo.com"),
        eq("New Animal Added"),
        contains("Simba")
    );
}
```

## Common Patterns

### When to Use Each Type

**Use Mocks when:**
- You need to verify method calls
- You want to ensure interactions happen correctly
- You're testing behavior, not data

**Use Stubs when:**
- You need specific return values
- You want to test different scenarios
- You're testing data flow

**Use Spies when:**
- You want to monitor real object behavior
- You need both real behavior and verification
- You're testing integration points

### Best Practices

1. **Keep tests simple** - One concept per test
2. **Use descriptive names** - Make test purpose clear
3. **Follow AAA pattern** - Arrange, Act, Assert
4. **Verify only what matters** - Don't over-verify
5. **Use meaningful data** - Make test data realistic

## Exercise Solutions

All TODO exercises include detailed comments with step-by-step instructions. Solutions are provided in the test file comments to help you learn the patterns.

## Next Steps

After completing Lab 0.5, you'll be ready for:
- **Lab 1**: Basic unit testing with JUnit 5
- **Lab 1.5**: MockMvc testing basics
- **Lab 2**: Controller testing with MockMvc
- **Lab 3**: JPA persistence testing

## Troubleshooting

### Common Issues
1. **Tests not running**: Check @ExtendWith(MockitoExtension.class)
2. **Mocks not working**: Ensure @Mock and @InjectMocks are used correctly
3. **Verification failing**: Check method signatures and arguments

### Useful Commands
```bash
# Run all tests
./gradlew test

# Run specific test class
./gradlew test --tests MockExamplesTest

# Run with debug output
./gradlew test --info

# Clean and rebuild
./gradlew clean test
```

## Resources

- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Test Double Patterns](https://martinfowler.com/bliki/TestDouble.html) 