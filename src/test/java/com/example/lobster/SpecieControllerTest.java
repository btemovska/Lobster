package com.example.lobster;

import com.example.lobster.controllers.SpecieController;
import com.example.lobster.model.Specie;
import com.example.lobster.repositories.SpecieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SpecieControllerTest {

    @InjectMocks
    private SpecieController underTest;

    @Mock
    private SpecieRepository specieRepo;
    @Mock
    private Specie specie1;
    @Mock
    private Specie specie2;
    @Mock
    private Model mockModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllSpecies(){
        Collection<Specie> allSpecies = Arrays.asList(specie1, specie2);
        when(specieRepo.findAll()).thenReturn(allSpecies);
        underTest.displaySpecies(mockModel);
        verify(mockModel).addAttribute("species", allSpecies);
    }

    @Test
    public void shouldAddNewSpecie(){
        underTest.addSpecie("Clawed");
        verify(specieRepo).save(new Specie( "Clawed"));
    }

    @Test
    public void shouldAddSpecieAndRedirectToSpeciesEndPoint(){
        String result = underTest.addSpecie("Clawed");
        assertThat(result).isEqualTo("redirect:species");
    }
}
