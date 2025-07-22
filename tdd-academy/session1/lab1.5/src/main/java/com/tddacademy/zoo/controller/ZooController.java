package com.tddacademy.zoo.controller;

import com.tddacademy.zoo.model.Zoo;
import com.tddacademy.zoo.service.ZooService;
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
} 