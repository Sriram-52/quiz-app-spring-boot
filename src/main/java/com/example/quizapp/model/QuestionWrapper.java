package com.example.quizapp.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuestionWrapper {
    private Long id;
    private String category;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public static QuestionWrapper fromQuestion(Question question) {
        return QuestionWrapper.builder()
                .id(question.getId())
                .category(question.getCategory())
                .question(question.getQuestion())
                .option1(question.getOption1())
                .option2(question.getOption2())
                .option3(question.getOption3())
                .option4(question.getOption4())
                .build();
    }
}
