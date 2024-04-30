package com.example.quizapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnswerDto {
    private Long questionId;
    private int choosenOption;
}
