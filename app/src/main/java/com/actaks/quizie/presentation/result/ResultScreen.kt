package com.actaks.quizie.presentation.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.actaks.quizie.R
import com.actaks.quizie.domain.model.QuizQuestion
import com.actaks.quizie.domain.model.UserAnswer
import com.actaks.quizie.presentation.theme.CustomGreen
import com.actaks.quizie.presentation.theme.QuizieTheme

@Composable
fun ResultScreen(
    state: ResultState
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(12.dp)
        ) {
            item {
                ScoreCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 80.dp, horizontal = 16.dp),
                    correctAnswers = state.correctAnswers,
                    totalQuestions = state.totalQuestions
                )
            }
            item {
                Text(
                    text = "Quiz Questions",
                    style = MaterialTheme.typography.titleLarge,
                    textDecoration = TextDecoration.Underline
                )
            }
            items(state.quizQuestions) { question ->
                val userAnswer = state.userAnswers
                    .find { it.questionId == question.id }?.selectedOption
                QuestionItem(
                    question = question,
                    userAnswer = userAnswer,
                    onReportIconClick = {}
                )
            }
        }
        Button(
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {}
        ) {
            Text(
                text = "Start new quiz"
            )
        }
    }
}

@Composable
private fun ScoreCard(
    correctAnswers: Int,
    totalQuestions: Int,
    modifier: Modifier = Modifier
) {
    val scorePercent = (correctAnswers * 100) / totalQuestions
    val resultText = when (scorePercent) {
        in 71..100 -> "Congratulations!\n Great performance!"
        in 41..70 -> "You did well!\n Keep up the good work!"
        else -> "You may have struggled this time.\n Keep trying!"
    }
    val resultIconResId = when (scorePercent) {
        in 71..100 -> R.drawable.ic_laugh
        in 41..70 -> R.drawable.ic_smiley
        else -> R.drawable.ic_sad
    }
    Card(modifier = modifier) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
                .size(100.dp),
            painter = painterResource(resultIconResId),
            contentDescription = null
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "You answered $correctAnswers out of $totalQuestions questions correctly!",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center,
            text = resultText,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun QuestionItem(
    question: QuizQuestion,
    onReportIconClick: () -> Unit,
    userAnswer: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Q: ${question.question}",
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = onReportIconClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Report"
                )
            }
        }
        question.allOptions.forEachIndexed { index, option ->
            val optionIndex = when (index) {
                0 -> "(a) "
                1 -> "(b) "
                2 -> "(c) "
                3 -> "(d) "
                else -> ""
            }
            val optionColor = when (option) {
                question.correctAnswer -> CustomGreen
                userAnswer -> MaterialTheme.colorScheme.error
                else -> LocalContentColor.current
            }
            Text(
                text = optionIndex + option,
                color = optionColor
            )

        }
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            text = question.explanation,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun ResultScreenPreview() {
    val dummyQuestions = List(20) { index ->
        QuizQuestion(
            id = "$index",
            topicCode = index,
            question = "What is the capital of India?",
            allOptions = listOf("New Delhi", "Mumbai", "Kolkata", "Chennai"),
            correctAnswer = "New Delhi",
            explanation = "The capital of India is New Delhi."
        )
    }
    val dummyAnswers = List(2) { index ->
        UserAnswer(
            questionId = "$index",
            selectedOption = dummyQuestions[index].allOptions.last(),
        )
    }
    QuizieTheme {
        ResultScreen(
            state = ResultState(
                correctAnswers = 7,
                totalQuestions = 10,
                quizQuestions = dummyQuestions,
                userAnswers = dummyAnswers
            )
        )
    }
}