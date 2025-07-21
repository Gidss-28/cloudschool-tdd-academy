package com.tddacademy.zoo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name = "people")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Person first name cannot be null or empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @NotBlank(message = "Person last name cannot be null or empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @NotBlank(message = "Person role cannot be null or empty")
    @Column(nullable = false)
    private String role;
    
    @NotBlank(message = "Person email cannot be null or empty")
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(name = "hire_date")
    private LocalDate hireDate;
    
    @Positive(message = "Person salary must be positive")
    @Column
    private Double salary;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zoo_id")
    private Zoo zoo;
    
    // Default constructor for JPA
    protected Person() {}
    
    public Person(String firstName, String lastName, String role, String email, LocalDate hireDate, Double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.hireDate = hireDate;
        this.salary = salary;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getHireDate() {
        return hireDate;
    }
    
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    
    public Double getSalary() {
        return salary;
    }
    
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
    public Zoo getZoo() {
        return zoo;
    }
    
    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }
} 