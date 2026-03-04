package com.actaks.quizie.presentation.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.actaks.quizie.domain.model.QuizQuestion
import com.actaks.quizie.domain.model.UserAnswer
import com.actaks.quizie.presentation.common.ErrorScreen
import com.actaks.quizie.presentation.quiz.components.ExitDialog
import com.actaks.quizie.presentation.quiz.components.Loading
import com.actaks.quizie.presentation.quiz.components.SubmitButton
import com.actaks.quizie.presentation.quiz.components.SubmitDialog
import com.actaks.quizie.presentation.quiz.components.TopBar
import com.actaks.quizie.presentation.theme.QuizieTheme

@Composable
fun QuizScreen(state: QuizState) {
    SubmitDialog(
        isOpen = state.isSubmitQuizDialogOpen,
        onDismissDialog = { },
        onConfirmDialog = { },
    )
    ExitDialog(
        isOpen = state.isExitQuizDialogOpen,
        onDismissDialog = { },
        onConfirmDialog = { },
    )
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = state.topBarTitle,
            onExitClick = { },
        )
        if (state.isLoading)
            Loading(
                message = state.loadingMessage,
            )
        else
            when {
                state.errorMessage != null -> ErrorScreen(
                    modifier = Modifier.fillMaxSize(),
                    errorMessage = state.errorMessage,
                    onRefreshClick = { })

                state.questions.isEmpty() -> ErrorScreen(
                    modifier = Modifier.fillMaxSize(),
                    errorMessage = "No quiz question available",
                    onRefreshClick = { })

                else -> QuizScreenContent(
                    state = state
                )
            }
    }
}

@Composable
private fun QuizScreenContent(
    state: QuizState, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        QuizQuestionNavigationRow(
            questions = state.questions,
            answers = state.answers,
            currentQuestionIndex = state.currentQuestionIndex,
            onTabSelected = { index ->
            })
        Spacer(modifier = Modifier.height(12.dp))
        QuestionItem(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            questions = state.questions,
            answers = state.answers,
            currentQuestionIndex = state.currentQuestionIndex,
            onOptionSelected = { questionId, option ->
            })
        SubmitButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            isPreviousButtonEnabled = state.currentQuestionIndex != 0,
            isNextButtonEnabled = state.currentQuestionIndex != state.questions.lastIndex,
            onPreviousButtonClick = {},
            onNextButtonClick = {})
    }
}

@Composable
private fun QuizQuestionNavigationRow(
    questions: List<QuizQuestion>,
    answers: List<UserAnswer>,
    currentQuestionIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    SecondaryScrollableTabRow(
        modifier = modifier, selectedTabIndex = currentQuestionIndex, edgePadding = 0.dp
    ) {
        questions.forEachIndexed { index, question ->
            val containerColor = when {
                answers.any { it.questionId == question.id } -> MaterialTheme.colorScheme.secondaryContainer
                else -> MaterialTheme.colorScheme.surface
            }
            Tab(
                modifier = Modifier.background(containerColor),
                selected = currentQuestionIndex == index,
                onClick = { onTabSelected(index) },
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 12.dp), text = "${index + 1}"
                )
            }
        }
    }
}

@Composable
private fun QuestionItem(
    questions: List<QuizQuestion>,
    answers: List<UserAnswer>,
    currentQuestionIndex: Int,
    onOptionSelected: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val question = questions[currentQuestionIndex]
        val selectedAnswer = answers.find { it.questionId == question.id }?.selectedOption
        Text(
            text = question.question, style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            question.allOptions.forEach { option ->
                OptionItem(
                    modifier = Modifier
                        .widthIn(min = 400.dp)
                        .padding(vertical = 8.dp),
                    option = option,
                    isSelected = option == selectedAnswer,
                    onClick = { onOptionSelected(question.id, option) })
            }
        }
    }
}

@Composable
private fun OptionItem(
    option: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.small
            ), colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        )) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isSelected, onClick = onClick
            )
            Text(
                text = option, style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@PreviewScreenSizes
@Composable
private fun QuizScreenPreview() {
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
        QuizScreen(
            state = QuizState(
                questions = dummyQuestions, answers = dummyAnswers, currentQuestionIndex = 2
            )
        )
    }
}