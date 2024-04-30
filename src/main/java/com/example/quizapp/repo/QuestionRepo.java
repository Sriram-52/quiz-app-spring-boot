package com.example.quizapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.quizapp.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long>{

    @Query("SELECT q FROM Question q ORDER BY id ASC")
    List<Question> findAll();

    @Query("SELECT q FROM Question q WHERE LOWER(q.category) like LOWER(%?1%) ORDER BY id ASC")
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question WHERE category = ?1 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
    List<Question> findRandomQuestions(String category, int limit);
}
