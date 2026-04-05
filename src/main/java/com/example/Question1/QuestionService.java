package com.example.Question1;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {



    private QuestionRepo repo;

    public QuestionService(QuestionRepo repo) {
        this.repo = repo;
    }

    public List<QuizQuestions> getAllQuestions(){
        return repo.findAll();
    }

    public List<QuizQuestions> getByCategory(String category){
        return repo.findByCategory(category);
    }
public QuizQuestions create(QuizQuestions que){
        return repo.save(que);
}
}
