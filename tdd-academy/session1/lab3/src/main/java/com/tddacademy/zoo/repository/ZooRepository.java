package com.tddacademy.zoo.repository;

import com.tddacademy.zoo.model.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZooRepository extends JpaRepository<Zoo, Long> {
    
    List<Zoo> findByNameContainingIgnoreCase(String name);
    
    List<Zoo> findByLocationContainingIgnoreCase(String location);
    
    Optional<Zoo> findByNameAndLocation(String name, String location);
    
    boolean existsByNameAndLocation(String name, String location);
} 