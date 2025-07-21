package com.tddacademy.zoo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zoos")
public class Zoo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Zoo name cannot be null or empty")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Zoo location cannot be null or empty")
    @Column(nullable = false)
    private String location;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enclosure> enclosures = new ArrayList<>();
    
    @OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Person> people = new ArrayList<>();
    
    // Default constructor for JPA
    protected Zoo() {}
    
    public Zoo(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Enclosure> getEnclosures() {
        return enclosures;
    }
    
    public void setEnclosures(List<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }
    
    public List<Person> getPeople() {
        return people;
    }
    
    public void setPeople(List<Person> people) {
        this.people = people;
    }
    
    // Helper methods
    public void addEnclosure(Enclosure enclosure) {
        enclosures.add(enclosure);
        enclosure.setZoo(this);
    }
    
    public void addPerson(Person person) {
        people.add(person);
        person.setZoo(this);
    }
} 