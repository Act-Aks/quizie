package com.actaks.quizie.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.actaks.quizie.domain.model.QuizTopic
import com.actaks.quizie.presentation.common.ErrorScreen
import com.actaks.quizie.presentation.dashboard.components.EditNameDialog
import com.actaks.quizie.presentation.dashboard.components.ShimmerEffect
import com.actaks.quizie.presentation.dashboard.components.TopicCard
import com.actaks.quizie.presentation.dashboard.components.UserStatisticsCard
import com.actaks.quizie.presentation.theme.QuizieTheme

@Composable
fun DashboardScreen(
    state: DashboardState,
) {
    EditNameDialog(
        value = state.userNameInput,
        isOpen = state.isEditNameDialogOpen,
        error = state.userNameError,
        onValueChange = { },
        onDismissDialog = { },
        onConfirmDialog = { }
    )
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(
            modifier = Modifier
                .fillMaxWidth(),
            userName = state.userName,
            questionAttempted = state.questionAttempted,
            rightAnswers = state.rightAnswers,
            onEditNameClick = { /*TODO*/ }
        )
        QuizTopic(
            modifier = Modifier.fillMaxWidth(),
            topics = state.quizTopics,
            isTopicsLoading = state.isLoading,
            errorMessage = state.errorMessage,
            onRefreshClick = { /* TODO */ }
        )
    }
}

@Composable
private fun Header(
    userName: String,
    questionAttempted: Int,
    rightAnswers: Int,
    onEditNameClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(top = 40.dp, start = 12.dp, end = 12.dp)
        ) {
            Text(
                text = "Hello",
                style = MaterialTheme.typography.bodyMedium
            )
            Row() {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.headlineMedium
                )
                IconButton(
                    modifier = Modifier.offset(x = (-10).dp, y = (-20).dp),
                    onClick = onEditNameClick,
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Name",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
        UserStatisticsCard(
            modifier = Modifier
                .widthIn(max = 400.dp)
                .padding(12.dp),
            questionsAttempted = questionAttempted,
            rightAnswers = rightAnswers
        )
    }
}

@Composable
private fun QuizTopic(
    topics: List<QuizTopic>,
    isTopicsLoading: Boolean,
    errorMessage: String?,
    onRefreshClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "What topic do you want to improve today?",
            style = MaterialTheme.typography.titleLarge
        )
        if (errorMessage != null)
            ErrorScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                errorMessage = errorMessage,
                onRefreshClick = onRefreshClick
            )
        else
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                if (isTopicsLoading)
                    items(count = 6) {
                        ShimmerEffect(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.small)
                                .fillMaxWidth()
                                .height(120.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                        )
                    }
                else
                    items(topics) { topic ->
                        TopicCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            topicName = topic.name,
                            imageUrl = topic.imageUrl,
                            onClick = {}
                        )
                    }
            }
    }
}

//@Preview(showBackground = true)
@PreviewScreenSizes
@Composable
private fun DashboardScreenPreview() {
    val quizTopics = List(20) { index ->
        QuizTopic(
            id = "$index",
            name = "Android",
            imageUrl = "",
            code = 0
        )
    }

    QuizieTheme {
        DashboardScreen(
            state = DashboardState(
                userName = "John Doe",
                questionAttempted = 10,
                rightAnswers = 5,
                quizTopics = quizTopics,
            )
        )
    }
}