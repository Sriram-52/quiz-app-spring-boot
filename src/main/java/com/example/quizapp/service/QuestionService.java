package com.example.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.model.Question;
import com.example.quizapp.repo.QuestionRepo;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo repository;

    public ResponseEntity<Question> create(Question question) {
        try {
            return ResponseEntity.ok(repository.save(question));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<Question>> findAll(Optional<String> category) {
        try {
            if (category.isPresent()) {
                return ResponseEntity.ok(repository.findByCategory(category.get()));
            }
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    public ResponseEntity<Question> findOne(Long id) {
        try {
            Question question = repository.findById(id).orElseThrow();
            return ResponseEntity.ok(question);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Question> update(Long id, Question question) {
        try {
            Question existingQuestion = repository.findById(id).orElseThrow();
            existingQuestion.setId(id);
            return ResponseEntity.ok(repository.save(existingQuestion));
        } catch (Exception e) {
            if (e instanceof NoSuchElementException) {
                return ResponseEntity.notFound().build();
            }
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> delete(Long id) {
        try {
            Question question = repository.findById(id).orElseThrow();
            repository.delete(question);
            return ResponseEntity.ok("Question deleted successfully");
        } catch (Exception e) {
            if (e instanceof NoSuchElementException) {
                return ResponseEntity.notFound().build();
            }
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
