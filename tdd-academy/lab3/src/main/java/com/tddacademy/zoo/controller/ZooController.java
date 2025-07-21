package com.tddacademy.zoo.controller;

import com.tddacademy.zoo.model.Zoo;
import com.tddacademy.zoo.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zoos")
public class ZooController {
    
    private final ZooService zooService;
    
    @Autowired
    public ZooController(ZooService zooService) {
        this.zooService = zooService;
    }
    
    @GetMapping
    public ResponseEntity<List<Zoo>> getAllZoos() {
        List<Zoo> zoos = zooService.getAllZoos();
        return ResponseEntity.ok(zoos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Zoo> getZooById(@PathVariable Long id) {
        Optional<Zoo> zoo = zooService.getZooById(id);
        if (zoo.isPresent()) {
            return ResponseEntity.ok(zoo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Zoo> createZoo(@RequestBody Zoo zoo) {
        Zoo createdZoo = zooService.createZoo(zoo);
        return ResponseEntity.ok(createdZoo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Zoo> updateZoo(@PathVariable Long id, @RequestBody Zoo zooDetails) {
        try {
            Zoo updatedZoo = zooService.updateZoo(id, zooDetails);
            return ResponseEntity.ok(updatedZoo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZoo(@PathVariable Long id) {
        try {
            zooService.deleteZoo(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/search/name")
    public ResponseEntity<List<Zoo>> searchZoosByName(@RequestParam String name) {
        List<Zoo> zoos = zooService.findZoosByName(name);
        return ResponseEntity.ok(zoos);
    }
    
    @GetMapping("/search/location")
    public ResponseEntity<List<Zoo>> searchZoosByLocation(@RequestParam String location) {
        List<Zoo> zoos = zooService.findZoosByLocation(location);
        return ResponseEntity.ok(zoos);
    }
} 