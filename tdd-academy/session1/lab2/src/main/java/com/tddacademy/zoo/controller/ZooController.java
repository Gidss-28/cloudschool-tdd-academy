package com.tddacademy.zoo.controller;

import com.tddacademy.zoo.model.Zoo;
import com.tddacademy.zoo.service.ZooService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zoos")
public class ZooController {
    
    private final ZooService zooService;
    
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
        try {
            Zoo zoo = zooService.getZooById(id);
            return ResponseEntity.ok(zoo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Zoo> createZoo(@RequestBody Zoo zoo) {
        try {
            Zoo createdZoo = zooService.createZoo(zoo);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdZoo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Zoo> updateZoo(@PathVariable Long id, @RequestBody Zoo zoo) {
        try {
            Zoo updatedZoo = zooService.updateZoo(id, zoo);
            return ResponseEntity.ok(updatedZoo);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
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
} 