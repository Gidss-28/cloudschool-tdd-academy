# Lab 3 Summary: JPA Persistence with H2 Database

## Test Overview

Lab 3 contains **25 tests** across three testing layers, with **12 completed tests** and **13 TODO exercises** for students to complete.

## Test Breakdown

### Repository Tests (6 tests)
**✅ Completed (3 tests):**
1. `shouldSaveZooSuccessfully()` - Basic save operation
2. `shouldFindZooById()` - Find by ID operation
3. `shouldFindAllZoos()` - Find all operation

**📝 TODO Exercises (3 tests):**
1. `shouldFindZoosByNameContaining()` - Custom query method
2. `shouldFindZoosByLocationContaining()` - Location search
3. `shouldCheckIfZooExistsById()` - Existence checks
4. `shouldDeleteZooById()` - Delete operation

### Service Tests (9 tests)
**✅ Completed (5 tests):**
1. `shouldGetAllZoos()` - Get all zoos
2. `shouldGetZooByIdWhenExists()` - Get by ID (exists)
3. `shouldReturnEmptyWhenZooNotFound()` - Get by ID (not found)
4. `shouldCreateZooSuccessfully()` - Create operation
5. `shouldFindZoosByName()` - Search by name
6. `shouldCheckIfZooExists()` - Existence check

**📝 TODO Exercises (4 tests):**
1. `shouldUpdateZooWhenExists()` - Update operation
2. `shouldThrowExceptionWhenUpdatingNonExistentZoo()` - Update error handling
3. `shouldDeleteZooWhenExists()` - Delete operation
4. `shouldThrowExceptionWhenDeletingNonExistentZoo()` - Delete error handling

### Controller Tests (10 tests)
**✅ Completed (6 tests):**
1. `shouldGetAllZoos()` - GET all zoos
2. `shouldGetZooByIdWhenExists()` - GET by ID (exists)
3. `shouldReturn404WhenZooNotFound()` - GET by ID (404)
4. `shouldCreateZooSuccessfully()` - POST create zoo
5. `shouldSearchZoosByName()` - GET search by name
6. `shouldSearchZoosByLocation()` - GET search by location

**📝 TODO Exercises (4 tests):**
1. `shouldUpdateZooWhenExists()` - PUT update zoo
2. `shouldReturn404WhenUpdatingNonExistentZoo()` - PUT 404 error
3. `shouldDeleteZooWhenExists()` - DELETE zoo
4. `shouldReturn404WhenDeletingNonExistentZoo()` - DELETE 404 error

## TODO Exercise Solutions

### Repository Layer Solutions

#### 1. Find Zoos by Name Containing
```java
@Test
@DisplayName("Should find zoos by name containing")
void shouldFindZoosByNameContaining() {
    // Given
    zooRepository.save(manilaZoo);
    zooRepository.save(cebuSafari);

    // When
    List<Zoo> manilaZoos = zooRepository.findByNameContainingIgnoreCase("Manila");

    // Then
    assertEquals(1, manilaZoos.size());
    assertEquals("Manila Zoo", manilaZoos.get(0).getName());
}
```

#### 2. Find Zoos by Location Containing
```java
@Test
@DisplayName("Should find zoos by location containing")
void shouldFindZoosByLocationContaining() {
    // Given
    zooRepository.save(manilaZoo);
    zooRepository.save(cebuSafari);

    // When
    List<Zoo> philippineZoos = zooRepository.findByLocationContainingIgnoreCase("Philippines");

    // Then
    assertEquals(2, philippineZoos.size());
    assertTrue(philippineZoos.stream().anyMatch(zoo -> zoo.getName().equals("Manila Zoo")));
    assertTrue(philippineZoos.stream().anyMatch(zoo -> zoo.getName().equals("Cebu Safari")));
}
```

#### 3. Check Existence and Delete
```java
@Test
@DisplayName("Should check if zoo exists by id")
void shouldCheckIfZooExistsById() {
    // Given
    Zoo savedZoo = zooRepository.save(manilaZoo);
    Long savedId = savedZoo.getId();

    // When & Then
    assertTrue(zooRepository.existsById(savedId));
    assertFalse(zooRepository.existsById(999L));
}

@Test
@DisplayName("Should delete zoo by id")
void shouldDeleteZooById() {
    // Given
    Zoo savedZoo = zooRepository.save(manilaZoo);
    Long savedId = savedZoo.getId();

    // When
    zooRepository.deleteById(savedId);
    Optional<Zoo> deletedZoo = zooRepository.findById(savedId);

    // Then
    assertTrue(deletedZoo.isEmpty());
}
```

### Service Layer Solutions

#### 1. Update Zoo When Exists
```java
@Test
@DisplayName("Should update zoo when exists")
void shouldUpdateZooWhenExists() {
    // Given
    Long zooId = 1L;
    manilaZoo.setId(zooId);
    Zoo updatedZoo = new Zoo("Updated Manila Zoo", "Updated Location", "Updated description");

    when(zooRepository.findById(zooId)).thenReturn(Optional.of(manilaZoo));
    when(zooRepository.save(any(Zoo.class))).thenReturn(updatedZoo);

    // When
    Zoo result = zooService.updateZoo(zooId, updatedZoo);

    // Then
    assertEquals("Updated Manila Zoo", result.getName());
    verify(zooRepository, times(1)).save(any(Zoo.class));
}
```

#### 2. Handle Update Errors
```java
@Test
@DisplayName("Should throw exception when updating non-existent zoo")
void shouldThrowExceptionWhenUpdatingNonExistentZoo() {
    // Given
    Long zooId = 999L;
    Zoo updatedZoo = new Zoo("Updated Zoo", "Updated Location", "Updated description");

    when(zooRepository.findById(zooId)).thenReturn(Optional.empty());

    // When & Then
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> zooService.updateZoo(zooId, updatedZoo)
    );
    assertTrue(exception.getMessage().contains("Zoo not found with id: 999"));
}
```

#### 3. Delete Zoo When Exists
```java
@Test
@DisplayName("Should delete zoo when exists")
void shouldDeleteZooWhenExists() {
    // Given
    Long zooId = 1L;
    when(zooRepository.existsById(zooId)).thenReturn(true);

    // When
    zooService.deleteZoo(zooId);

    // Then
    verify(zooRepository, times(1)).deleteById(zooId);
}
```

#### 4. Handle Delete Errors
```java
@Test
@DisplayName("Should throw exception when deleting non-existent zoo")
void shouldThrowExceptionWhenDeletingNonExistentZoo() {
    // Given
    Long zooId = 999L;
    when(zooRepository.existsById(zooId)).thenReturn(false);

    // When & Then
    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> zooService.deleteZoo(zooId)
    );
    assertTrue(exception.getMessage().contains("Zoo not found with id: 999"));
}
```

### Controller Layer Solutions

#### 1. Update Zoo Via REST
```java
@Test
@DisplayName("Should update zoo when exists")
void shouldUpdateZooWhenExists() throws Exception {
    // Given
    manilaZoo.setId(1L);
    Zoo updatedZoo = new Zoo("Updated Manila Zoo", "Updated Location", "Updated description");
    updatedZoo.setId(1L);

    when(zooService.updateZoo(eq(1L), any(Zoo.class))).thenReturn(updatedZoo);

    // When & Then
    mockMvc.perform(put("/api/zoos/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedZoo)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Updated Manila Zoo"));
}
```

#### 2. Handle Update 404
```java
@Test
@DisplayName("Should return 404 when updating non-existent zoo")
void shouldReturn404WhenUpdatingNonExistentZoo() throws Exception {
    // Given
    Zoo updatedZoo = new Zoo("Updated Zoo", "Updated Location", "Updated description");

    when(zooService.updateZoo(eq(999L), any(Zoo.class)))
            .thenThrow(new IllegalArgumentException("Zoo not found with id: 999"));

    // When & Then
    mockMvc.perform(put("/api/zoos/999")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedZoo)))
            .andExpect(status().isNotFound());
}
```

#### 3. Delete Zoo Via REST
```java
@Test
@DisplayName("Should delete zoo when exists")
void shouldDeleteZooWhenExists() throws Exception {
    // Given
    doNothing().when(zooService).deleteZoo(1L);

    // When & Then
    mockMvc.perform(delete("/api/zoos/1"))
            .andExpect(status().isNoContent());
}
```

#### 4. Handle Delete 404
```java
@Test
@DisplayName("Should return 404 when deleting non-existent zoo")
void shouldReturn404WhenDeletingNonExistentZoo() throws Exception {
    // Given
    doThrow(new IllegalArgumentException("Zoo not found with id: 999"))
            .when(zooService).deleteZoo(999L);

    // When & Then
    mockMvc.perform(delete("/api/zoos/999"))
            .andExpect(status().isNotFound());
}
```

## Key Learning Points

### JPA Concepts
- **Entity Relationships**: One-to-Many, Many-to-One mappings
- **ID Generation**: Auto-increment primary keys
- **Column Constraints**: Not null, unique constraints
- **Validation**: Bean validation annotations

### Testing Strategies
- **@DataJpaTest**: Repository testing with in-memory database
- **@WebMvcTest**: Controller testing with mocked service
- **Mockito**: Service layer testing with mocked repository
- **TestEntityManager**: Direct database operations in tests

### Database Operations
- **CRUD Operations**: Create, Read, Update, Delete
- **Custom Queries**: Method name-based queries
- **Transaction Management**: Automatic transaction handling
- **Error Handling**: Exception handling for database operations

## Common Patterns

### Repository Pattern
```java
public interface ZooRepository extends JpaRepository<Zoo, Long> {
    List<Zoo> findByNameContainingIgnoreCase(String name);
    List<Zoo> findByLocationContainingIgnoreCase(String location);
}
```

### Service Layer Pattern
```java
@Service
public class ZooService {
    public Zoo updateZoo(Long id, Zoo zooDetails) {
        Optional<Zoo> optionalZoo = zooRepository.findById(id);
        if (optionalZoo.isPresent()) {
            Zoo zoo = optionalZoo.get();
            // Update fields
            return zooRepository.save(zoo);
        } else {
            throw new IllegalArgumentException("Zoo not found with id: " + id);
        }
    }
}
```

### Controller Error Handling
```java
@PutMapping("/{id}")
public ResponseEntity<Zoo> updateZoo(@PathVariable Long id, @RequestBody Zoo zooDetails) {
    try {
        Zoo updatedZoo = zooService.updateZoo(id, zooDetails);
        return ResponseEntity.ok(updatedZoo);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
    }
}
```

## Next Steps

After completing Lab 3, students will be ready for:
- **Lab 4**: Complete CRUD for Animals, Enclosures, and People
- **Lab 5**: API security implementation
- **Lab 6**: Advanced testing with real database containers 