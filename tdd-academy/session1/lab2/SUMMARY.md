# Lab 2 Summary

## ✅ What We Accomplished

### 1. **REST API Implementation**
- Created `ZooController` with full CRUD operations
- Implemented proper HTTP status codes (200, 201, 204, 400, 404)
- Added JSON request/response handling
- Implemented error handling with appropriate responses

### 2. **Service Layer**
- Created `ZooService` for business logic
- Implemented in-memory storage using HashMap
- Added proper error handling and validation
- Created atomic ID generation

### 3. **Comprehensive Testing Strategy**
- **Unit Tests**: 10 test methods for service layer
- **Controller Tests**: 7 completed + 6 exercise tests for REST API
- **Total**: 17 completed + 6 exercise test methods

## 🎯 Key Learning Objectives Achieved

### REST API Design
- ✅ Proper HTTP method usage (GET, POST, PUT, DELETE)
- ✅ Consistent URL patterns (`/api/zoos`)
- ✅ Appropriate status codes and responses
- ✅ JSON request/response handling
- ✅ Error handling with proper HTTP status codes

### Testing Patterns
- ✅ MockMvc for controller testing
- ✅ Service layer unit testing
- ✅ Error scenario testing
- ✅ Content type validation
- ✅ Request/response validation
- ✅ Exercise-based learning approach

### Spring Boot Features
- ✅ @RestController annotation
- ✅ @RequestMapping for URL mapping
- ✅ @RequestBody for JSON deserialization
- ✅ ResponseEntity for response control
- ✅ Exception handling in controllers

## 📊 Test Coverage

### ZooService Tests (10 methods)
- Empty list handling
- Zoo creation with validation
- Zoo retrieval by ID
- Zoo updates
- Zoo deletion
- Error scenarios for non-existent resources
- Multiple zoos management

### ZooController Tests (7 completed + 6 exercises)
**Completed Tests:**
- GET all zoos (empty and populated)
- GET zoo by ID (non-existing)
- POST create zoo (valid data)
- Malformed JSON handling in POST
- Content type validation for all endpoints

**Exercise Tests (TODO):**
- GET zoo by ID (existing)
- PUT update zoo (valid and non-existing)
- DELETE zoo (existing and non-existing)
- Malformed JSON handling in PUT

## 🚀 API Endpoints Implemented

| Method | Endpoint | Description | Status Codes |
|--------|----------|-------------|--------------|
| GET | `/api/zoos` | Get all zoos | 200 |
| GET | `/api/zoos/{id}` | Get zoo by ID | 200, 404 |
| POST | `/api/zoos` | Create new zoo | 201, 400 |
| PUT | `/api/zoos/{id}` | Update zoo | 200, 400, 404 |
| DELETE | `/api/zoos/{id}` | Delete zoo | 204, 404 |

## 🔧 Technical Implementation

### Service Layer Pattern
```java
@Service
public class ZooService {
    private final Map<Long, Zoo> zoos = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    // CRUD operations with proper error handling
}
```

### REST Controller Pattern
```java
@RestController
@RequestMapping("/api/zoos")
public class ZooController {
    // HTTP method mappings with proper status codes
    // Exception handling for different scenarios
}
```

### Testing Patterns
```java
@WebMvcTest(ZooController.class)
class ZooControllerTest {
    // MockMvc testing with request/response validation
    // TODO exercises for student learning
}
```

## 📋 What Students Should Understand

1. **REST API Design**: How to create proper REST endpoints with correct HTTP methods and status codes
2. **Service Layer**: How to separate business logic from controllers
3. **Testing Strategies**: How to test different layers (unit, controller)
4. **Error Handling**: How to handle exceptions and return appropriate HTTP responses
5. **Request/Response Testing**: How to validate JSON requests and responses
6. **MockMvc**: How to test REST controllers without starting the full application
7. **Exercise Completion**: How to follow TODO instructions to complete tests

## 🔄 Next Steps
This lab provides the foundation for:
- **Lab 3**: Adding JPA persistence with H2 database
- **Lab 4**: Complete CRUD for all resources (Enclosures, Animals, People)
- **Lab 5**: API security with API keys

## 🎉 Success Criteria
- [x] REST API endpoints working correctly
- [x] Proper HTTP status codes implemented
- [x] Service layer with business logic
- [x] Comprehensive test coverage (17 completed + 6 exercises)
- [x] Error handling for various scenarios
- [x] JSON request/response handling
- [x] MockMvc testing for REST controllers
- [x] Exercise-based learning approach

## 📝 Notes for Students
- The application demonstrates proper REST API design patterns
- Tests show how to validate both successful and error scenarios
- MockMvc testing provides confidence in controller behavior
- Error handling ensures robust API behavior
- The service layer pattern separates concerns effectively
- TODO exercises help reinforce learning through hands-on practice

## 🎯 Exercise Solutions

### Exercise 1: Complete `shouldReturnZooWhenItExists()`
```java
when(zooService.getZooById(1L)).thenReturn(createdZoo);
mockMvc.perform(get("/api/zoos/1"))
    .andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    .andExpect(jsonPath("$.id").value(1))
    .andExpect(jsonPath("$.name").value("Manila Zoo"))
    .andExpect(jsonPath("$.location").value("Manila, Philippines"))
    .andExpect(jsonPath("$.description").value("A beautiful zoo in the heart of Manila"));
```

### Exercise 2: Complete `shouldUpdateZooSuccessfully()`
```java
Zoo updatedZoo = new Zoo(1L, "Updated Zoo Name", "Updated Location", "Updated description", new ArrayList<>(), new ArrayList<>());
when(zooService.updateZoo(eq(1L), any(Zoo.class))).thenReturn(updatedZoo);
mockMvc.perform(put("/api/zoos/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(testZoo)))
    .andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    .andExpect(jsonPath("$.id").value(1))
    .andExpect(jsonPath("$.name").value("Updated Zoo Name"))
    .andExpect(jsonPath("$.location").value("Updated Location"))
    .andExpect(jsonPath("$.description").value("Updated description"));
```

### Exercise 3: Complete `shouldReturn404WhenUpdatingNonExistentZoo()`
```java
when(zooService.updateZoo(eq(999L), any(Zoo.class))).thenThrow(new IllegalArgumentException("Zoo not found with id: 999"));
mockMvc.perform(put("/api/zoos/999")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(testZoo)))
    .andExpect(status().isNotFound());
```

### Exercise 4: Complete `shouldDeleteZooSuccessfully()`
```java
doNothing().when(zooService).deleteZoo(1L);
mockMvc.perform(delete("/api/zoos/1"))
    .andExpect(status().isNoContent());
```

### Exercise 5: Complete `shouldReturn404WhenDeletingNonExistentZoo()`
```java
doThrow(new IllegalArgumentException("Zoo not found with id: 999")).when(zooService).deleteZoo(999L);
mockMvc.perform(delete("/api/zoos/999"))
    .andExpect(status().isNotFound());
```

### Exercise 6: Complete `shouldHandleMalformedJsonInPutRequest()`
```java
mockMvc.perform(put("/api/zoos/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ invalid json }"))
    .andExpect(status().isBadRequest());
``` 