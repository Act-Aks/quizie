package com.actaks.quizie.presentation.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.actaks.quizie.presentation.dashboard.components.UserStatisticsCard
import com.actaks.quizie.presentation.theme.QuizieTheme

@Composable
fun DashboardScreen(
    state: DashboardState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(
            modifier = Modifier
                .padding(top = 40.dp, start = 12.dp, end = 12.dp),
            userName = state.userName,
            questionAttempted = state.questionAttempted,
            rightAnswers = state.rightAnswers,
            onEditNameClick = { /*TODO*/ }
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
    Column(
        modifier = modifier
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
        UserStatisticsCard(
            questionsAttempted = questionAttempted,
            rightAnswers = rightAnswers
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DashboardScreenPreview() {
    QuizieTheme {
        DashboardScreen(
            state = DashboardState(
                userName = "John Doe",
                questionAttempted = 10,
                rightAnswers = 5
            )
        )
    }
}