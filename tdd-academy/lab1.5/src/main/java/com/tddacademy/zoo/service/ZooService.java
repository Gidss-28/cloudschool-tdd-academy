package com.tddacademy.zoo.service;

import com.tddacademy.zoo.model.Zoo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ZooService {
    
    private final Map<Long, Zoo> zoos = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    public ZooService() {
        // Add some sample data for testing
        Zoo manilaZoo = new Zoo(1L, "Manila Zoo", "Manila, Philippines", 
                               "A beautiful zoo in the heart of Manila", new ArrayList<>(), new ArrayList<>());
        Zoo cebuSafari = new Zoo(2L, "Cebu Safari", "Cebu, Philippines", 
                                "World famous safari park", new ArrayList<>(), new ArrayList<>());
        
        zoos.put(1L, manilaZoo);
        zoos.put(2L, cebuSafari);
    }
    
    public List<Zoo> getAllZoos() {
        return new ArrayList<>(zoos.values());
    }
    
    public Zoo getZooById(Long id) {
        Zoo zoo = zoos.get(id);
        if (zoo == null) {
            throw new IllegalArgumentException("Zoo not found with id: " + id);
        }
        return zoo;
    }
} 