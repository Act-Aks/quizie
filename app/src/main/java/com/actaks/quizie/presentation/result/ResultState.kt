package com.actaks.quizie.presentation.result

import com.actaks.quizie.domain.model.QuizQuestion
import com.actaks.quizie.domain.model.UserAnswer

data class ResultState(
    val correctAnswers: Int = 0,
    val totalQuestions: Int = 0,
    val quizQuestions: List<QuizQuestion> = emptyList(),
    val userAnswers: List<UserAnswer> = emptyList()
)
