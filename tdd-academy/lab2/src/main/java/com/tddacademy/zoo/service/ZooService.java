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
    
    public Zoo createZoo(Zoo zoo) {
        Long newId = idGenerator.getAndIncrement();
        Zoo newZoo = new Zoo(newId, zoo.name(), zoo.location(), zoo.description(), 
                           zoo.enclosures(), zoo.people());
        zoos.put(newId, newZoo);
        return newZoo;
    }
    
    public Zoo updateZoo(Long id, Zoo zoo) {
        if (!zoos.containsKey(id)) {
            throw new IllegalArgumentException("Zoo not found with id: " + id);
        }
        Zoo updatedZoo = new Zoo(id, zoo.name(), zoo.location(), zoo.description(), 
                                zoo.enclosures(), zoo.people());
        zoos.put(id, updatedZoo);
        return updatedZoo;
    }
    
    public void deleteZoo(Long id) {
        if (!zoos.containsKey(id)) {
            throw new IllegalArgumentException("Zoo not found with id: " + id);
        }
        zoos.remove(id);
    }
    
    public boolean zooExists(Long id) {
        return zoos.containsKey(id);
    }
} 