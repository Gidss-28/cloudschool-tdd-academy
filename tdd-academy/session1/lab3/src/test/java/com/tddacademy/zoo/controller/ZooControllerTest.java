package com.tddacademy.zoo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tddacademy.zoo.model.Zoo;
import com.tddacademy.zoo.service.ZooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ZooController.class)
class ZooControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZooService zooService;

    @Autowired
    private ObjectMapper objectMapper;

    private Zoo manilaZoo;
    private Zoo cebuSafari;

    @BeforeEach
    void setUp() {
        manilaZoo = new Zoo("Manila Zoo", "Manila, Philippines", "A beautiful zoo in the heart of Manila");
        cebuSafari = new Zoo("Cebu Safari", "Cebu, Philippines", "World famous safari park");
    }

    @Test
    @DisplayName("Should get all zoos")
    void shouldGetAllZoos() throws Exception {
        // Given
        List<Zoo> zoos = Arrays.asList(manilaZoo, cebuSafari);
        when(zooService.getAllZoos()).thenReturn(zoos);

        // When & Then
        mockMvc.perform(get("/api/zoos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Manila Zoo"))
                .andExpect(jsonPath("$[1].name").value("Cebu Safari"));
    }

    @Test
    @DisplayName("Should get zoo by id when exists")
    void shouldGetZooByIdWhenExists() throws Exception {
        // Given
        manilaZoo.setId(1L);
        when(zooService.getZooById(1L)).thenReturn(Optional.of(manilaZoo));

        // When & Then
        mockMvc.perform(get("/api/zoos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Manila Zoo"));
    }

    @Test
    @DisplayName("Should return 404 when zoo not found")
    void shouldReturn404WhenZooNotFound() throws Exception {
        // Given
        when(zooService.getZooById(999L)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/zoos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should create zoo successfully")
    void shouldCreateZooSuccessfully() throws Exception {
        // Given
        manilaZoo.setId(1L);
        when(zooService.createZoo(any(Zoo.class))).thenReturn(manilaZoo);

        // When & Then
        mockMvc.perform(post("/api/zoos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(manilaZoo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Manila Zoo"));
    }

    @Test
    @DisplayName("Should update zoo when exists")
    void shouldUpdateZooWhenExists() throws Exception {
        // Your code here:
        manilaZoo.setId(1L);
        Zoo updatedZoo = new Zoo("Updated Manila Zoo", "Updated Location", "Updated description");
        updatedZoo.setId(1L);

        when(zooService.updateZoo(eq(1L), any(Zoo.class))).thenReturn(updatedZoo);

        mockMvc.perform(put("/api/zoos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedZoo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Manila Zoo"));
    }

    @Test
    @DisplayName("Should return 404 when updating non-existent zoo")
    void shouldReturn404WhenUpdatingNonExistentZoo() throws Exception {
        // Your code here:
        Zoo updatedZoo = new Zoo("Updated Zoo", "Updated Location", "Updated description");

        when(zooService.updateZoo(eq(999L), any(Zoo.class)))
                .thenThrow(new IllegalArgumentException("Zoo not found with id: 999"));

        mockMvc.perform(put("/api/zoos/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedZoo)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should delete zoo when exists")
    void shouldDeleteZooWhenExists() throws Exception {
        // Your code here:
        doNothing().when(zooService).deleteZoo(1L);

        mockMvc.perform(delete("/api/zoos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should return 404 when deleting non-existent zoo")
    void shouldReturn404WhenDeletingNonExistentZoo() throws Exception {
        // Your code here:
        doThrow(new IllegalArgumentException("Zoo not found with id: 999"))
                .when(zooService).deleteZoo(999L);

        mockMvc.perform(delete("/api/zoos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should search zoos by name")
    void shouldSearchZoosByName() throws Exception {
        // Given
        List<Zoo> zoos = Arrays.asList(manilaZoo);
        when(zooService.findZoosByName("Manila")).thenReturn(zoos);

        // When & Then
        mockMvc.perform(get("/api/zoos/search/name")
                        .param("name", "Manila"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Manila Zoo"));
    }

    @Test
    @DisplayName("Should search zoos by location")
    void shouldSearchZoosByLocation() throws Exception {
        // Given
        List<Zoo> zoos = Arrays.asList(manilaZoo, cebuSafari);
        when(zooService.findZoosByLocation("Philippines")).thenReturn(zoos);

        // When & Then
        mockMvc.perform(get("/api/zoos/search/location")
                        .param("location", "Philippines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Manila Zoo"))
                .andExpect(jsonPath("$[1].name").value("Cebu Safari"));
    }
}