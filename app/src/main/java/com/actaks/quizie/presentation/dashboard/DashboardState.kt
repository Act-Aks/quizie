package com.actaks.quizie.presentation.dashboard

import com.actaks.quizie.domain.model.QuizTopic

data class DashboardState(
    val userName: String = "Android Developer",
    val questionAttempted: Int = 0,
    val rightAnswers: Int = 0,
    val quizTopics: List<QuizTopic> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isEditNameDialogOpen: Boolean = false,
    val userNameInput: String = "",
    val userNameError: String? = null,
)
