package com.likelion.rest.Repository;

import com.likelion.rest.entity.Tutorial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TutorialRepositoryTest {
    @Autowired
    private TutorialRepository tutorialRepository;

    @BeforeEach
    void setUp() {
        tutorialRepository.save(new Tutorial(null, "Title 1", "1st Tutorial", true));
        tutorialRepository.save(new Tutorial(null, "Title 2", "Desc 2", true));
        tutorialRepository.save(new Tutorial(null, "Title 3", "power", false));
        tutorialRepository.save(new Tutorial(null, "Title 4", "No published", false));
    }

    @AfterEach
    void tearDown() {
        tutorialRepository.deleteAll();
    }
    @Test
    void save() {
        assertThat(tutorialRepository.save(new Tutorial(null, "Title 5", "Desc 5", true))).isNotNull();
    }
    @Test
    void findByPublished() {
        List<Tutorial> tutorialList = new ArrayList<>();
        tutorialList.add(new Tutorial(1l, "Title 1", "1st Tutorial", true));
        tutorialList.add(new Tutorial(2l, "Title 2", "Desc 2", true));
        assertThat(tutorialRepository.findByPublished(true).get(0).toString())
                .isEqualTo(tutorialList.get(0).toString());
        assertThat(tutorialRepository.findByPublished(true).get(1).toString())
                .isEqualTo(tutorialList.get(1).toString());
    }

    @Test
    void findByTitleContaining() {
        Tutorial tutorial = new Tutorial(1l, "Title 1", "1st Tutorial", true);
        assertThat(tutorialRepository.findByTitleContaining("title").get(0).getDescription())
                .isEqualTo(tutorial.getDescription());
    }
    @Test
    void deleteById() {
        tutorialRepository.deleteById(1l);
        assertThat(tutorialRepository.findById(1l)).isEmpty();
    }

    @Test
    void deleteAll() {
        tutorialRepository.deleteAll();
        assertThat(tutorialRepository.findById(1l)).isEmpty();
        assertThat(tutorialRepository.findById(2l)).isEmpty();
        assertThat(tutorialRepository.findById(3l)).isEmpty();
        assertThat(tutorialRepository.findById(4l)).isEmpty();
    }
}