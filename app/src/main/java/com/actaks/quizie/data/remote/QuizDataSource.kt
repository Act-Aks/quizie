package com.actaks.quizie.data.remote

import com.actaks.quizie.data.remote.dto.QuizTopicDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class QuizDataSource(private val httpClient: HttpClient) {
    suspend fun getQuizTopics(): List<QuizTopicDto>? {
        return try {
            val response = httpClient.get(urlString = constructUrl("/quiz-topics"))
            response.body<List<QuizTopicDto>>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}