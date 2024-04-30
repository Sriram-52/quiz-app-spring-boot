package com.example.quizapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.model.Question;
import com.example.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @PostMapping()
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return service.create(question);
    }
    
    @GetMapping()
    public ResponseEntity<List<Question>> getQuestions(Optional<String> category) {
        return service.findAll(category);
    }

    @GetMapping("{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable("id") Long id) {
        return service.findOne(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") Long id, @RequestBody Question question) {
        return service.update(id, question);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
