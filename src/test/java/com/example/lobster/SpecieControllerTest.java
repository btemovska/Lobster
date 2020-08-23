package com.example.lobster;

import com.example.lobster.model.Specie;
import com.example.lobster.repositories.LobsterRepository;
import com.example.lobster.repositories.SpecieRepository;
import com.example.lobster.rest.controllers.SpecieRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SpecieControllerTest {
    @Mock
    private SpecieRepository specieRepo;
    @Mock
    private LobsterRepository lobsterRepo;

    @InjectMocks
    private SpecieRestController underTest;

    private Specie testSpecie;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testSpecie = new Specie ("TestSpecie");
    }

    @Test
    public void t1shouldReturnAllSpecies() {
        when(specieRepo.findAll()).thenReturn(Collections.singletonList(testSpecie));
        Collection<Specie> result = underTest.getSpecies();
        assertThat(result).containsOnly(testSpecie);
    }

    @Test
    public void t2fetchAllEndpointReturnsAllSpecies() throws Exception {
        when(specieRepo.findAll()).thenReturn(Collections.singletonList(testSpecie));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/api/species/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type", is("TestSpecie")));

    }

    @Test
    public void t3retrieveByIdShouldReturnASpecificSpecieById() {
        when(specieRepo.findById(1L)).thenReturn(Optional.of(testSpecie));
        Specie result = underTest.getSpecie(1L);
        assertThat(result).isEqualTo(testSpecie);
    }

    @Test
    public void t4fetchByIdEndpointReturnASpecificLobster() throws Exception {
        when(specieRepo.findById(1L)).thenReturn(Optional.of(testSpecie));
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/api/species/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is("TestSpecie")));
    }

}
