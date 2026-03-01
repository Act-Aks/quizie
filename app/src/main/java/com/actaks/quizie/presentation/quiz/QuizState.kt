package com.actaks.quizie.presentation.quiz

import com.actaks.quizie.domain.model.QuizQuestion
import com.actaks.quizie.domain.model.UserAnswer

data class QuizState(
    val questions: List<QuizQuestion> = emptyList(),
    val answers: List<UserAnswer> = emptyList(),
    val currentQuestionIndex: Int = 0
)
