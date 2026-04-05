package com.example.Question1;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    private QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("all")
    public List<QuizQuestions> getallquestions(){
        return service.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public List<QuizQuestions> getByCat(@PathVariable String category){
        return service.getByCategory(category);
    }
    @PostMapping("create")
    public QuizQuestions create(@RequestBody QuizQuestions que){
        return service.create(que);
    }
}
