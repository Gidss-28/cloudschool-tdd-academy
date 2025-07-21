# Lab 1: Base Spring Boot Application with Java Records

## Overview
This lab introduces the fundamentals of Spring Boot 3.5.3 with Java 17 records and basic JUnit testing. We'll create a Zoo Simulator application with domain models using Java records.

## Learning Objectives
- Set up a basic Spring Boot application
- Understand Java records for immutable data classes
- Write comprehensive JUnit tests
- Learn validation patterns in records
- Understand the relationship between domain models

## Domain Model
The Zoo Simulator has the following entities:
- **Zoo**: Contains enclosures and people
- **Enclosure**: Contains animals, belongs to a Zoo
- **Animal**: Belongs to an Enclosure
- **Person**: Belongs to a Zoo

## Project Structure
```
lab1/
├── src/
│   ├── main/
│   │   ├── java/com/tddacademy/zoo/
│   │   │   ├── ZooApplication.java
│   │   │   └── model/
│   │   │       ├── Zoo.java
│   │   │       ├── Enclosure.java
│   │   │       ├── Animal.java
│   │   │       └── Person.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/tddacademy/zoo/
│           ├── ZooApplicationTests.java
│           └── model/
│               ├── ZooTest.java
│               ├── EnclosureTest.java
│               ├── AnimalTest.java
│               └── PersonTest.java
├── build.gradle
└── README.md
```

## Key Features

### Java Records
- Immutable data classes with built-in validation
- Compact syntax for domain models
- Automatic getter methods
- Built-in `equals()`, `hashCode()`, and `toString()`

### Validation
Each record includes validation logic:
- Required fields cannot be null or empty
- Numeric values must be positive
- Email validation for Person records

### Testing
Comprehensive JUnit 5 tests covering:
- Valid object creation
- Validation error scenarios
- Edge cases (null values, negative numbers)

## How to Run

### Prerequisites
- Java 17 or higher
- Gradle 8.0 or higher

### Running the Application
```bash
cd lab1
./gradlew bootRun
```

### Running Tests
```bash
cd lab1
./gradlew test
```

### Building the Application
```bash
cd lab1
./gradlew build
```

## Test Coverage
The application includes tests for:
- **Zoo**: 5 test methods covering creation and validation
- **Enclosure**: 6 test methods covering creation and validation
- **Animal**: 6 test methods covering creation and validation
- **Person**: 7 test methods covering creation and validation

## Expected Output
When you run the tests, you should see:
```
BUILD SUCCESSFUL in Xs
24 actionable tasks: 24 executed
```

## Next Steps
This lab provides the foundation for:
- Lab 2: Adding REST controllers and CRUD operations
- Lab 3: Implementing JPA persistence
- Lab 4: Complete CRUD for all resources
- Lab 5: API security with API keys

## Troubleshooting
- Ensure Java 17 is installed and set as JAVA_HOME
- Make sure Gradle is properly installed
- Check that all dependencies are resolved in build.gradle 