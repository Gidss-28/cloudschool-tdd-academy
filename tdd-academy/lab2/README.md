# Lab 2: REST API with CRUD Operations and Comprehensive Testing

## Overview
This lab builds upon Lab 1 by adding a complete REST API for Zoo management with comprehensive request/response testing. Students will learn how to create REST controllers, implement CRUD operations, and test both unit and controller scenarios.

## Learning Objectives
- Create REST controllers with proper HTTP methods
- Implement service layer for business logic
- Write comprehensive unit tests for services
- Write REST API tests using MockMvc
- Understand HTTP status codes and response handling
- Test error scenarios and edge cases
- Complete exercises to reinforce learning

## New Features Added

### 1. **Service Layer**
- `ZooService`: Handles business logic for Zoo operations
- In-memory storage using HashMap
- Proper error handling and validation

### 2. **REST Controller**
- `ZooController`: Exposes REST endpoints for Zoo CRUD operations
- Proper HTTP status codes (200, 201, 204, 400, 404)
- JSON request/response handling
- Error handling with appropriate responses

### 3. **Comprehensive Testing**
- **Unit Tests**: Service layer testing
- **Controller Tests**: REST API testing with MockMvc
- **Exercise Tests**: TODO exercises for students to complete

## API Endpoints

| Method | Endpoint | Description | Status Codes |
|--------|----------|-------------|--------------|
| GET | `/api/zoos` | Get all zoos | 200 |
| GET | `/api/zoos/{id}` | Get zoo by ID | 200, 404 |
| POST | `/api/zoos` | Create new zoo | 201, 400 |
| PUT | `/api/zoos/{id}` | Update zoo | 200, 400, 404 |
| DELETE | `/api/zoos/{id}` | Delete zoo | 204, 404 |

## Project Structure
```
lab2/
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
│       │   ├── service/
│       │   │   └── ZooServiceTest.java
│       │   └── controller/
│       │       └── ZooControllerTest.java
│       └── resources/
│           └── application-test.properties
├── build.gradle
└── README.md
```

## Testing Strategy

### 1. **Unit Tests (ZooServiceTest)**
- Tests business logic in isolation
- 10 test methods covering all CRUD operations
- Error scenario testing
- Edge case validation

### 2. **Controller Tests (ZooControllerTest)**
- **Completed Tests**: 7 test methods with full implementation
- **Exercise Tests**: 6 TODO exercises for students to complete
- Request/response validation
- Error handling testing
- Content type validation

## Key Testing Concepts Demonstrated

### MockMvc Testing
```java
@WebMvcTest(ZooController.class)
class ZooControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void shouldCreateZooSuccessfully() throws Exception {
        mockMvc.perform(post("/api/zoos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(zoo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Manila Zoo"));
    }
}
```

### HTTP Status Code Testing
- **200 OK**: Successful GET and PUT operations
- **201 Created**: Successful POST operations
- **204 No Content**: Successful DELETE operations
- **400 Bad Request**: Invalid input data
- **404 Not Found**: Resource not found

## How to Run

### Prerequisites
- Java 17 or higher
- Gradle 8.0 or higher

### Running the Application
```bash
cd lab2
./gradlew bootRun
```

### Running Tests
```bash
cd lab2
./gradlew test
```

### Testing the API Manually
```bash
# Get all zoos
curl http://localhost:8080/api/zoos

# Create a zoo
curl -X POST http://localhost:8080/api/zoos \
  -H "Content-Type: application/json" \
  -d '{"name":"Manila Zoo","location":"Manila, Philippines","description":"A beautiful zoo in the heart of Manila"}'

# Get a specific zoo
curl http://localhost:8080/api/zoos/1

# Update a zoo
curl -X PUT http://localhost:8080/api/zoos/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Updated Zoo","location":"Updated Location","description":"Updated description"}'

# Delete a zoo
curl -X DELETE http://localhost:8080/api/zoos/1
```

## Test Coverage
- **ZooService**: 10 test methods
- **ZooController**: 7 completed + 6 exercise tests
- **Total**: 17 completed + 6 exercise test methods

## Expected Test Output
After completing the exercises:
```
BUILD SUCCESSFUL in Xs
23 actionable tasks: 23 executed
```

## Exercises

### Exercise 1: Complete `shouldReturnZooWhenItExists()`
**Goal**: Test that GET /api/zoos/1 returns the correct Manila Zoo data.

**Steps**:
1. Set up the mock: `when(zooService.getZooById(1L)).thenReturn(createdZoo);`
2. Use `mockMvc.perform(get("/api/zoos/1"))` to make a GET request
3. Add expectations for HTTP 200, JSON content type, and all zoo fields

### Exercise 2: Complete `shouldUpdateZooSuccessfully()`
**Goal**: Test that PUT /api/zoos/1 updates a zoo successfully.

**Steps**:
1. Create an updated zoo object
2. Set up the mock to return the updated zoo
3. Use `mockMvc.perform(put("/api/zoos/1"))` with JSON content
4. Add expectations for HTTP 200 and updated fields

### Exercise 3: Complete `shouldReturn404WhenUpdatingNonExistentZoo()`
**Goal**: Test that PUT /api/zoos/999 returns a 404 status.

**Steps**:
1. Set up the mock to throw an exception
2. Use `mockMvc.perform(put("/api/zoos/999"))` with JSON content
3. Add expectation for HTTP 404

### Exercise 4: Complete `shouldDeleteZooSuccessfully()`
**Goal**: Test that DELETE /api/zoos/1 deletes a zoo successfully.

**Steps**:
1. Set up the mock: `doNothing().when(zooService).deleteZoo(1L);`
2. Use `mockMvc.perform(delete("/api/zoos/1"))` to make a DELETE request
3. Add expectation for HTTP 204 (No Content)

### Exercise 5: Complete `shouldReturn404WhenDeletingNonExistentZoo()`
**Goal**: Test that DELETE /api/zoos/999 returns a 404 status.

**Steps**:
1. Set up the mock to throw an exception
2. Use `mockMvc.perform(delete("/api/zoos/999"))` to make a DELETE request
3. Add expectation for HTTP 404

### Exercise 6: Complete `shouldHandleMalformedJsonInPutRequest()`
**Goal**: Test that PUT with malformed JSON returns a 400 status.

**Steps**:
1. Use `mockMvc.perform(put("/api/zoos/1"))` with invalid JSON content
2. Add expectation for HTTP 400 (Bad Request)

## Key Learning Points

### REST API Design
- Proper HTTP method usage
- Consistent URL patterns
- Appropriate status codes
- JSON request/response handling

### Testing Patterns
- Given-When-Then structure
- MockMvc for controller testing
- Service layer unit testing
- Error scenario testing
- Content type validation

### Spring Boot Features
- @RestController annotation
- @RequestMapping for URL mapping
- @RequestBody for JSON deserialization
- ResponseEntity for response control
- Exception handling

## Next Steps
This lab provides the foundation for:
- **Lab 3**: Adding JPA persistence with H2 database
- **Lab 4**: Complete CRUD for all resources (Enclosures, Animals, People)
- **Lab 5**: API security with API keys

## Troubleshooting
- Ensure all dependencies are resolved
- Check that the application starts on port 8080
- Verify that all tests pass before proceeding
- Use the provided curl commands to test the API manually
- Follow the TODO comments in the test file for guidance 