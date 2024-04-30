package com.example.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.model.CreateQuizDto;
import com.example.quizapp.model.QuestionWrapper;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.ValidateQuizDto;
import com.example.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService service;

    @PostMapping()
    public ResponseEntity<Quiz> create(@RequestBody CreateQuizDto createQuizDto) {
        return service.create(createQuizDto);
    }

    @PostMapping("validate")
    public ResponseEntity<String> validate(@RequestBody ValidateQuizDto validateQuizDto) {
        return service.validate(validateQuizDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<QuestionWrapper>> findOne(@PathVariable("id") Long id) {
        return service.findOne(id);
    }
}
