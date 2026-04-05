package com.example.Question1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class) // Initializes Mockito for JUnit 5
class QuestionServiceTest {

    @Mock
    private QuestionRepo repo; // Mocking the dependency

    @InjectMocks
    private QuestionService service; // Injecting mocks into the service

    private QuizQuestions sampleQuestion;

    @BeforeEach
    void setUp() {
        sampleQuestion = new QuizQuestions();
        sampleQuestion.setNumber(1);
        sampleQuestion.setQuestiontitle("What is Spring Boot?");
        sampleQuestion.setCategory("Java");
        sampleQuestion.setDifficultylevel("Easy");
    }

    @Test
    @DisplayName("Should return a list of all questions from the repository")
    void getAllQuestions_ShouldReturnList() {
        // Given (Arrange)
        given(repo.findAll()).willReturn(List.of(sampleQuestion));

        // When (Act)
        List<QuizQuestions> result = service.getAllQuestions();

        // Then (Assert)
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getQuestiontitle()).isEqualTo("What is Spring Boot?");
        verify(repo, times(1)).findAll(); // Verifies the repo was actually called
    }

    @Test
    @DisplayName("Should return questions filtered by category")
    void getByCategory_ShouldReturnFilteredList() {
        // Given
        String category = "Java";
        given(repo.findByCategory(category)).willReturn(List.of(sampleQuestion));

        // When
        List<QuizQuestions> result = service.getByCategory(category);

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getCategory()).isEqualTo(category);
        verify(repo).findByCategory(category);
    }

    @Test
    @DisplayName("Should save and return the question object")
    void create_ShouldSaveQuestion() {
        // Given
        given(repo.save(any(QuizQuestions.class))).willReturn(sampleQuestion);

        // When
        QuizQuestions savedQuestion = service.create(sampleQuestion);

        // Then
        assertThat(savedQuestion).isNotNull();
        assertThat(savedQuestion.getQuestiontitle()).isEqualTo("What is Spring Boot?");
        verify(repo).save(sampleQuestion);
    }
}