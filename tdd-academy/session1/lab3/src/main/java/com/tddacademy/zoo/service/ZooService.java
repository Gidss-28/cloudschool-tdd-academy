package com.tddacademy.zoo.service;

import com.tddacademy.zoo.model.Zoo;
import com.tddacademy.zoo.repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZooService {
    
    private final ZooRepository zooRepository;
    
    @Autowired
    public ZooService(ZooRepository zooRepository) {
        this.zooRepository = zooRepository;
    }
    
    public List<Zoo> getAllZoos() {
        return zooRepository.findAll();
    }
    
    public Optional<Zoo> getZooById(Long id) {
        return zooRepository.findById(id);
    }
    
    public Zoo createZoo(Zoo zoo) {
        return zooRepository.save(zoo);
    }
    
    public Zoo updateZoo(Long id, Zoo zooDetails) {
        Optional<Zoo> optionalZoo = zooRepository.findById(id);
        if (optionalZoo.isPresent()) {
            Zoo zoo = optionalZoo.get();
            zoo.setName(zooDetails.getName());
            zoo.setLocation(zooDetails.getLocation());
            zoo.setDescription(zooDetails.getDescription());
            return zooRepository.save(zoo);
        } else {
            throw new IllegalArgumentException("Zoo not found with id: " + id);
        }
    }
    
    public void deleteZoo(Long id) {
        if (zooRepository.existsById(id)) {
            zooRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Zoo not found with id: " + id);
        }
    }
    
    public List<Zoo> findZoosByName(String name) {
        return zooRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Zoo> findZoosByLocation(String location) {
        return zooRepository.findByLocationContainingIgnoreCase(location);
    }
    
    public boolean zooExists(Long id) {
        return zooRepository.existsById(id);
    }
} 