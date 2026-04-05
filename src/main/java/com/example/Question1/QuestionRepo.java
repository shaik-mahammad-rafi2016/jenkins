package com.example.Question1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<QuizQuestions,Integer>{
    List<QuizQuestions> findByCategory(String category);
}
