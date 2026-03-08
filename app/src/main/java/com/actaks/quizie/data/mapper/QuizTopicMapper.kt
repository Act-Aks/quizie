package com.actaks.quizie.data.mapper

import com.actaks.quizie.data.remote.dto.QuizTopicDto
import com.actaks.quizie.domain.model.QuizTopic

fun QuizTopicDto.toQuizTopic() = QuizTopic(
    id = id,
    name = name,
    imageUrl = imageUrl,
    code = code,
)

fun List<QuizTopicDto>.toQuizTopics() = map { it.toQuizTopic() }