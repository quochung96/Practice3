package com.likelion.rest.Service.impl;

import com.likelion.rest.Repository.TutorialRepository;
import com.likelion.rest.entity.Tutorial;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class TutorialServiceImplTest {
    @Mock
    private TutorialRepository tutorialRepository;
    @InjectMocks
    private TutorialServiceImpl tutorialService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        List<Tutorial> tutorials = new ArrayList<>();
        tutorials.add(new Tutorial(1l, "Test tutorial 1", "This is a test tutorial 1", true));
        tutorials.add(new Tutorial(2l, "Test tutorial 2", "This is a test tutorial 2", false));

        when(tutorialRepository.findAll()).thenReturn(tutorials);

        List<Tutorial> returnedTutorials = tutorialService.findAll();

        assertEquals(2, returnedTutorials.size());
        assertEquals("Test tutorial 1", returnedTutorials.get(0).getTitle());
        assertEquals("This is a test tutorial 2", returnedTutorials.get(1).getDescription());
    }

    @Test
    void findByTitleContaining() {
        List<Tutorial> tutorials = new ArrayList<>();
        tutorials.add(new Tutorial(1l, "Test tutorial 1", "This is a test tutorial 1", true));

        when(tutorialRepository.findByTitleContaining("this")).thenReturn(tutorials);

        assertThat(tutorialService.findByTitleContaining("this")).isNotNull();
    }




    @Test
    void findById() {
        when(tutorialRepository.findById(1l))
                .thenReturn(Optional.of(new Tutorial(1l, "Title 1", "Description 1", true)));
        assertThat(tutorialService.findById(1l).getId()).isEqualTo(1l);
    }



    @Test
    void deleteById() {
        tutorialService.deleteById(1l);
        verify(tutorialRepository).deleteById(1l);
    }




    @Test
    void deleteAll() {
        tutorialService.deleteAll();
        verify(tutorialRepository).deleteAll();
    }

    @Test
    void findByPublished() {
        List<Tutorial> tutorials = new ArrayList<>();
        tutorials.add(new Tutorial(1l, "Test tutorial 1", "This is a test tutorial 1", true));

        when(tutorialRepository.findByPublished(true)).thenReturn(tutorials);
        List<Tutorial> returnedTutorials = tutorialService.findByPublished(true);

        assertThat(returnedTutorials.get(0).getId()).isEqualTo(1l);
    }

    @Test
    void saveTutorial() {
        Tutorial tutorial = new Tutorial(1l, "Test tutorial 1", "This is a test tutorial 1", true);

        when(tutorialRepository.save(tutorial)).thenReturn(tutorial);

        assertThat(tutorialService.saveTutorial(tutorial)).isNotNull();
    }
}