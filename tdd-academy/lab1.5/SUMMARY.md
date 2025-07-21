# Lab 1.5 Summary

## ✅ What We Accomplished

### 1. **Simple REST API Implementation**
- Created `ZooController` with basic GET endpoints
- Implemented proper HTTP status codes (200, 404)
- Added JSON response handling
- Simplified error handling

### 2. **Basic Service Layer**
- Created `ZooService` with pre-loaded sample data
- Two sample zoos: Manila Zoo and Cebu Safari
- Basic CRUD operations for demonstration

### 3. **MockMvc Testing Introduction**
- Simple test examples without complex Mockito
- Step-by-step guidance for exercises
- Focus on basic HTTP status and JSON validation

## 🎯 Key Learning Objectives Achieved

### MockMvc Basics
- ✅ Understanding what MockMvc is and why we use it
- ✅ Learning how to write simple REST API tests
- ✅ Practicing testing GET endpoints
- ✅ Understanding JSON response validation

### REST API Design
- ✅ Proper HTTP method usage (GET only for simplicity)
- ✅ Consistent URL patterns (`/api/zoos`)
- ✅ Appropriate status codes and responses
- ✅ JSON response handling

### Testing Patterns
- ✅ `@SpringBootTest` and `@AutoConfigureMockMvc` annotations
- ✅ Basic HTTP status testing
- ✅ JSON path validation
- ✅ Array and object response testing

## 📊 Test Coverage

### Completed Test (1 method)
- `shouldReturnAllZoos()`: Demonstrates how to test GET /api/zoos
- Shows JSON array validation
- Shows how to check specific array elements

### Exercise Tests (2 methods - TODO)
- `shouldReturnZooWhenItExists()`: Test GET /api/zoos/{id} for existing zoo
- `shouldReturn404WhenZooDoesNotExist()`: Test GET /api/zoos/{id} for non-existent zoo

## 🚀 API Endpoints Implemented

| Method | Endpoint | Description | Status Codes |
|--------|----------|-------------|--------------|
| GET | `/api/zoos` | Get all zoos | 200 |
| GET | `/api/zoos/{id}` | Get zoo by ID | 200, 404 |

## 🔧 Technical Implementation

### Simple REST Controller Pattern
```java
@RestController
@RequestMapping("/api/zoos")
public class ZooController {
    // Basic GET endpoints with proper status codes
    // Simple error handling for different scenarios
}
```

### MockMvc Testing Pattern
```java
@SpringBootTest
@AutoConfigureMockMvc
class ZooControllerTest {
    // MockMvc testing with request/response validation
    // No complex Mockito techniques
}
```

## 📋 What Students Should Understand

1. **MockMvc Basics**: How to test REST controllers without starting a full web server
2. **HTTP Status Codes**: Understanding 200 OK and 404 Not Found
3. **JSON Path Testing**: How to validate JSON responses using jsonPath
4. **Test Structure**: Given-When-Then pattern in REST API testing
5. **Exercise Completion**: How to follow TODO instructions to complete tests

## 🔄 Next Steps
This lab provides the foundation for:
- **Lab 2**: More advanced MockMvc testing with POST, PUT, DELETE
- **Lab 3**: Adding database persistence
- **Lab 4**: Complete CRUD operations
- **Lab 5**: API security

## 🎉 Success Criteria
- [x] REST API endpoints working correctly
- [x] Proper HTTP status codes implemented
- [x] Service layer with sample data
- [x] Basic test coverage (1 completed, 2 exercises)
- [x] Error handling for 404 scenarios
- [x] JSON response handling
- [x] MockMvc testing for REST controllers

## 📝 Notes for Students
- This lab focuses on simplicity and learning the basics
- No complex Mockito techniques are used
- The exercises are designed to be completed step-by-step
- The API responses are predictable and consistent
- Use the manual curl commands to understand the API behavior before writing tests

## 🎯 Exercise Solutions

### Exercise 1: Complete `shouldReturnZooWhenItExists()`
```java
mockMvc.perform(get("/api/zoos/1"))
    .andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    .andExpect(jsonPath("$.id").value(1))
    .andExpect(jsonPath("$.name").value("Manila Zoo"))
    .andExpect(jsonPath("$.location").value("Manila, Philippines"))
    .andExpect(jsonPath("$.description").value("A beautiful zoo in the heart of Manila"));
```

### Exercise 2: Complete `shouldReturn404WhenZooDoesNotExist()`
```java
mockMvc.perform(get("/api/zoos/999"))
    .andExpect(status().isNotFound());
``` 