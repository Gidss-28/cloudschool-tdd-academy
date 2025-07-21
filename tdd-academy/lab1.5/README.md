# Lab 1.5: Introduction to MockMvc Testing

## Overview
This lab introduces you to MockMvc testing in Spring Boot. You'll learn how to test REST API endpoints using MockMvc without complex Mockito techniques. This is perfect for beginners who want to understand the basics of testing REST APIs.

## Learning Objectives
- Understand what MockMvc is and why we use it
- Learn how to write simple REST API tests
- Practice testing GET endpoints
- Understand JSON response validation
- Complete exercises to reinforce learning

## What is MockMvc?
MockMvc is a testing framework that allows you to test Spring MVC controllers without starting a full web server. It simulates HTTP requests and validates responses, making tests fast and reliable.

## New Features Added

### 1. **Simple REST Controller**
- `ZooController`: Basic GET endpoints for Zoo operations
- Only GET methods (no POST, PUT, DELETE for simplicity)
- Proper HTTP status codes (200, 404)

### 2. **Basic Service Layer**
- `ZooService`: Simple service with pre-loaded sample data
- Two sample zoos: Manila Zoo and Cebu Safari
- Basic CRUD operations for demonstration

### 3. **MockMvc Testing**
- Simple test examples without complex Mockito
- Step-by-step guidance for exercises
- Focus on basic HTTP status and JSON validation

## API Endpoints

| Method | Endpoint | Description | Status Codes |
|--------|----------|-------------|--------------|
| GET | `/api/zoos` | Get all zoos | 200 |
| GET | `/api/zoos/{id}` | Get zoo by ID | 200, 404 |

## Project Structure
```
lab1.5/
├── src/
│   ├── main/
│   │   ├── java/com/tddacademy/zoo/
│   │   │   ├── ZooApplication.java
│   │   │   ├── controller/
│   │   │   │   └── ZooController.java
│   │   │   ├── service/
│   │   │   │   └── ZooService.java
│   │   │   └── model/
│   │   │       ├── Zoo.java
│   │   │       ├── Enclosure.java
│   │   │       ├── Animal.java
│   │   │       └── Person.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/com/tddacademy/zoo/
│       │   ├── ZooApplicationTests.java
│       │   └── controller/
│       │       └── ZooControllerTest.java
├── build.gradle
└── README.md
```

## Sample Data
The application comes with pre-loaded sample data:
- **Manila Zoo** (ID: 1) - "A beautiful zoo in the heart of Manila"
- **Cebu Safari** (ID: 2) - "World famous safari park"

## Testing Strategy

### 1. **Completed Test Example**
- `shouldReturnAllZoos()`: Shows how to test GET /api/zoos
- Demonstrates JSON array validation
- Shows how to check specific array elements

### 2. **Exercise Tests (TODO)**
- `shouldReturnZooWhenItExists()`: Test GET /api/zoos/{id} for existing zoo
- `shouldReturn404WhenZooDoesNotExist()`: Test GET /api/zoos/{id} for non-existent zoo

## Key Testing Concepts Demonstrated

### MockMvc Basic Structure
```java
@WebMvcTest(ZooController.class)
class ZooControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void shouldReturnAllZoos() throws Exception {
        mockMvc.perform(get("/api/zoos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}
```

### Common MockMvc Expectations
- **`status().isOk()`**: Check for HTTP 200 status
- **`status().isNotFound()`**: Check for HTTP 404 status
- **`content().contentType(MediaType.APPLICATION_JSON)`**: Check content type
- **`jsonPath("$.field").value(expectedValue)`**: Check JSON field values
- **`jsonPath("$").isArray()`**: Check if response is an array
- **`jsonPath("$[0].field").value(expectedValue)`**: Check array element fields

## How to Run

### Prerequisites
- Java 17 or higher
- Gradle 8.0 or higher

### Running the Application
```bash
cd lab1.5
./gradlew bootRun
```

### Running Tests
```bash
cd lab1.5
./gradlew test
```

### Testing the API Manually
```bash
# Get all zoos
curl http://localhost:8080/api/zoos

# Get a specific zoo
curl http://localhost:8080/api/zoos/1

# Try to get a non-existent zoo
curl http://localhost:8080/api/zoos/999
```

## Exercises

### Exercise 1: Complete the `shouldReturnZooWhenItExists()` Test
**Goal**: Test that GET /api/zoos/1 returns the correct Manila Zoo data.

**Steps**:
1. Uncomment the mockMvc.perform line
2. Add expectations for:
   - HTTP 200 status
   - JSON content type
   - Zoo ID = 1
   - Zoo name = "Manila Zoo"
   - Zoo location = "Manila, Philippines"
   - Zoo description = "A beautiful zoo in the heart of Manila"

**Hint**: Look at the completed test above for examples of how to write expectations.

### Exercise 2: Complete the `shouldReturn404WhenZooDoesNotExist()` Test
**Goal**: Test that GET /api/zoos/999 returns a 404 status.

**Steps**:
1. Uncomment the mockMvc.perform line
2. Add expectation for HTTP 404 status

**Hint**: Use `status().isNotFound()` for 404 status.

## Expected Test Output
After completing the exercises:
```
BUILD SUCCESSFUL in Xs
3 actionable tasks: 3 executed
```

## Key Learning Points

### MockMvc Basics
- `@WebMvcTest` annotation for controller testing
- `MockMvc` autowiring for test requests
- `perform()` method to make HTTP requests
- `andExpect()` method to validate responses

### HTTP Status Codes
- **200 OK**: Successful GET operations
- **404 Not Found**: Resource not found

### JSON Path Testing
- `$` represents the root of the JSON response
- `$[0]` represents the first element of an array
- `$.fieldName` represents a field in the JSON object

## Next Steps
After completing this lab, you'll be ready for:
- **Lab 2**: More advanced MockMvc testing with POST, PUT, DELETE
- **Lab 3**: Adding database persistence
- **Lab 4**: Complete CRUD operations
- **Lab 5**: API security

## Troubleshooting
- Make sure all imports are correct
- Check that the test method throws Exception
- Verify JSON path syntax matches the actual response structure
- Use the manual curl commands to understand the API response format 