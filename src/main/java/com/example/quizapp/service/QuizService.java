package com.example.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.model.AnswerDto;
import com.example.quizapp.model.CreateQuizDto;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionWrapper;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.ValidateQuizDto;
import com.example.quizapp.repo.QuestionRepo;
import com.example.quizapp.repo.QuizRepo;

@Service
public class QuizService {

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private QuizRepo quizRepo;

    public ResponseEntity<Quiz> create(CreateQuizDto createQuizDto) {
        try {
            Quiz quiz = new Quiz();
            quiz.setTitle(createQuizDto.getTitle());
            quiz.setDescription(createQuizDto.getDescription());
            quiz.setCategory(createQuizDto.getCategory());
            
            List<Question> questions = questionRepo.findRandomQuestions(createQuizDto.getCategory(), createQuizDto.getNumberOfQuestions());
            quiz.setQuestions(questions);

            Quiz savedQuiz = quizRepo.save(quiz);
            return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<QuestionWrapper>> findOne(Long id) {
        try {
            List<Question> questions = quizRepo.findById(id).orElseThrow().getQuestions();
            List<QuestionWrapper> questionWrappers = questions.stream().map(question -> QuestionWrapper.fromQuestion(question)).toList();
            return ResponseEntity.ok(questionWrappers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> validate(ValidateQuizDto validateQuizDto) {
        try {
            Quiz quiz = quizRepo.findById(validateQuizDto.getQuizId()).orElseThrow();
            List<Question> questions = quiz.getQuestions();
            List<AnswerDto> answers = validateQuizDto.getAnswers();
            int score = 0;
            for (Question question: questions) {
                int choosenAnswer = answers.stream().filter(answer -> answer.getQuestionId() == question.getId()).findFirst().get().getChoosenOption();
                if (choosenAnswer == question.getCorrectAnswer()) {
                    score++;
                }
            }
            return ResponseEntity.ok("Your score is: " + score + "/" + questions.size());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }   
}
