package com.tddacademy.zoo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enclosures")
public class Enclosure {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Enclosure name cannot be null or empty")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Enclosure type cannot be null or empty")
    @Column(nullable = false)
    private String type;
    
    @Positive(message = "Enclosure area must be positive")
    @Column
    private Double area;
    
    @Column
    private String climate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zoo_id")
    private Zoo zoo;
    
    @OneToMany(mappedBy = "enclosure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Animal> animals = new ArrayList<>();
    
    // Default constructor for JPA
    protected Enclosure() {}
    
    public Enclosure(String name, String type, Double area, String climate) {
        this.name = name;
        this.type = type;
        this.area = area;
        this.climate = climate;
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Double getArea() {
        return area;
    }
    
    public void setArea(Double area) {
        this.area = area;
    }
    
    public String getClimate() {
        return climate;
    }
    
    public void setClimate(String climate) {
        this.climate = climate;
    }
    
    public Zoo getZoo() {
        return zoo;
    }
    
    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }
    
    public List<Animal> getAnimals() {
        return animals;
    }
    
    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }
    
    // Helper methods
    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setEnclosure(this);
    }
} 