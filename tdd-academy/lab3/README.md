# Lab 3: JPA Persistence with H2 Database

## Overview

Lab 3 introduces **JPA (Java Persistence API)** and database persistence using **H2 in-memory database**. You'll learn how to create JPA entities, repositories, and test database operations.

## Learning Objectives

By the end of this lab, you will be able to:

- ✅ **Understand JPA Entities**: Create and configure JPA entities with annotations
- ✅ **Use Spring Data JPA**: Work with repositories and database operations
- ✅ **Test Database Operations**: Write tests for repository and service layers
- ✅ **Handle Database Relationships**: Understand entity relationships and mappings
- ✅ **Use H2 Database**: Work with in-memory database for testing

## Prerequisites

- Java 17 or higher
- Gradle 7.0 or higher
- Understanding of Spring Boot basics (Lab 1 & 2)

## Project Structure

```
lab3/
├── src/
│   ├── main/
│   │   ├── java/com/tddacademy/zoo/
│   │   │   ├── model/          # JPA Entities
│   │   │   ├── repository/     # Spring Data JPA Repositories
│   │   │   ├── service/        # Business Logic Layer
│   │   │   ├── controller/     # REST Controllers
│   │   │   └── ZooApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/tddacademy/zoo/
│           ├── repository/     # Repository Tests
│           ├── service/        # Service Tests
│           ├── controller/     # Controller Tests
│           └── ZooApplicationTests.java
├── build.gradle
└── README.md
```

## Key Concepts

### 1. JPA Entities
- **@Entity**: Marks a class as a JPA entity
- **@Table**: Specifies the database table name
- **@Id**: Marks a field as the primary key
- **@GeneratedValue**: Configures ID generation strategy
- **@Column**: Configures column properties
- **@OneToMany/@ManyToOne**: Defines relationships

### 2. Spring Data JPA
- **JpaRepository**: Provides CRUD operations
- **Custom Query Methods**: Define queries by method names
- **@Repository**: Marks a class as a data access component

### 3. H2 Database
- **In-memory database**: Perfect for testing
- **H2 Console**: Web-based database browser
- **Auto-configuration**: Spring Boot automatically configures H2

## Getting Started

### 1. Run the Application
```bash
./gradlew bootRun
```

### 2. Access H2 Console
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

### 3. Run Tests
```bash
./gradlew test
```

## API Endpoints

### Zoo Management
- `GET /api/zoos` - Get all zoos
- `GET /api/zoos/{id}` - Get zoo by ID
- `POST /api/zoos` - Create new zoo
- `PUT /api/zoos/{id}` - Update zoo
- `DELETE /api/zoos/{id}` - Delete zoo

### Search Operations
- `GET /api/zoos/search/name?name={name}` - Search zoos by name
- `GET /api/zoos/search/location?location={location}` - Search zoos by location

## Test Structure

### Repository Tests (6 tests)
- ✅ **3 Completed**: Basic CRUD operations
- 📝 **3 TODO**: Custom query methods

### Service Tests (9 tests)
- ✅ **5 Completed**: Basic service operations
- 📝 **4 TODO**: Update, delete, and error handling

### Controller Tests (10 tests)
- ✅ **6 Completed**: Basic REST operations
- 📝 **4 TODO**: Update, delete, and error responses

## TODO Exercises

### Repository Layer (3 exercises)
1. **Find zoos by name containing** - Test custom query method
2. **Find zoos by location containing** - Test location search
3. **Check existence and delete** - Test existence checks and deletion

### Service Layer (4 exercises)
1. **Update zoo when exists** - Test update operation
2. **Handle update errors** - Test error handling for non-existent zoo
3. **Delete zoo when exists** - Test delete operation
4. **Handle delete errors** - Test error handling for non-existent zoo

### Controller Layer (4 exercises)
1. **Update zoo via REST** - Test PUT endpoint
2. **Handle update 404** - Test error response for non-existent zoo
3. **Delete zoo via REST** - Test DELETE endpoint
4. **Handle delete 404** - Test error response for non-existent zoo

## Sample Data

The application uses Philippine zoo data:
- **Manila Zoo**: "A beautiful zoo in the heart of Manila"
- **Cebu Safari**: "World famous safari park"

## Database Schema

### Zoo Table
- `id` (Primary Key, Auto-generated)
- `name` (Not null)
- `location` (Not null)
- `description` (Text)

### Relationships
- Zoo → Enclosures (One-to-Many)
- Zoo → People (One-to-Many)
- Enclosure → Animals (One-to-Many)

## Next Steps

After completing Lab 3, you'll be ready for:
- **Lab 4**: Complete CRUD for all resources (Animals, Enclosures, People)
- **Lab 5**: API security with API keys
- **Lab 6**: Advanced testing with TestContainers

## Troubleshooting

### Common Issues
1. **H2 Console not accessible**: Check if application is running
2. **Tests failing**: Ensure all dependencies are downloaded
3. **Database connection issues**: Verify application.properties configuration

### Useful Commands
```bash
# Clean and build
./gradlew clean build

# Run with debug logging
./gradlew bootRun --debug

# Run specific test class
./gradlew test --tests ZooRepositoryTest
```

## Resources

- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [H2 Database Documentation](http://www.h2database.com/html/main.html)
- [JPA Specification](https://jakarta.ee/specifications/persistence/) 