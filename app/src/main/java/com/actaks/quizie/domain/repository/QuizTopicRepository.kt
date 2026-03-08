package com.actaks.quizie.domain.repository

import com.actaks.quizie.domain.model.QuizTopic

interface QuizTopicRepository {
    suspend fun getQuizTopics(): List<QuizTopic>?
}