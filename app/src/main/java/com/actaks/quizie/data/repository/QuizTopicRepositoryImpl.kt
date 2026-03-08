package com.actaks.quizie.data.repository

import com.actaks.quizie.data.mapper.toQuizTopics
import com.actaks.quizie.data.remote.HttpClientFactory
import com.actaks.quizie.data.remote.QuizDataSource
import com.actaks.quizie.domain.model.QuizTopic
import com.actaks.quizie.domain.repository.QuizTopicRepository

class QuizTopicRepositoryImpl : QuizTopicRepository {
    private val httpClient = HttpClientFactory.create()
    private val dataSource = QuizDataSource(httpClient = httpClient)

    override suspend fun getQuizTopics(): List<QuizTopic>? {
        val quizTopicDtos = dataSource.getQuizTopics()
        return quizTopicDtos?.toQuizTopics()
    }
}