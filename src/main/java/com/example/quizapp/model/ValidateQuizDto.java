package com.example.quizapp.model;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ValidateQuizDto {
    private Long quizId;
    private List<AnswerDto> answers;
}
