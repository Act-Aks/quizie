package com.actaks.quizie.presentation.quiz

import com.actaks.quizie.domain.model.QuizQuestion
import com.actaks.quizie.domain.model.UserAnswer

data class QuizState(
    val questions: List<QuizQuestion> = emptyList(),
    val answers: List<UserAnswer> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val errorMessage: String? = null,
    val topBarTitle: String = "Quiz",
    val loadingMessage: String? = null,
    val isLoading: Boolean = false,
    val isSubmitQuizDialogOpen: Boolean = false,
    val isExitQuizDialogOpen: Boolean = false
)
