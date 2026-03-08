package com.actaks.quizie.presentation.issueReport

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.actaks.quizie.domain.model.QuizQuestion
import com.actaks.quizie.presentation.issueReport.components.QuestionCard
import com.actaks.quizie.presentation.issueReport.components.TopBar
import com.actaks.quizie.presentation.theme.QuizieTheme

@Composable
fun IssueReportScreen(
    state: IssueReportState
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopBar(
            title = "Report an issue",
            onBackClick = {},
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            QuestionCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                question = state.quizQuestion,
                isExpanded = state.isExpanded,
                onExpandClick = {}
            )
            Spacer(modifier = Modifier.height(10.dp))
            IssueType(
                selectedIssueType = state.issueType,
                otherIssueText = state.otherIssueText,
                onIssueTypeClick = {},
                onOtherIssueTextChange = {}
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                value = state.additionalComment,
                onValueChange = {},
                label = { Text(text = "Additional comment") },
                supportingText = {
                    Text(text = "Describe the issue in detail (optional)")
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.followUpEmail,
                onValueChange = {},
                label = { Text(text = "Follow up email") },
                singleLine = true,
                supportingText = { Text(text = "(Optional)") }
            )
        }
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp),
            onClick = {}
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "Submit issue"
            )
        }
    }
}

@Composable
private fun IssueType(
    selectedIssueType: IssueType,
    otherIssueText: String,
    onIssueTypeClick: (IssueType) -> Unit,
    onOtherIssueTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "ISSUE TYPE")
        FlowRow() {
            IssueType.entries.forEach { issue ->
                Row(
                    modifier = Modifier
                        .widthIn(250.dp)
                        .clickable { onIssueTypeClick(issue) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = issue == selectedIssueType,
                        onClick = { onIssueTypeClick(issue) }
                    )
                    if (issue == IssueType.OTHER)
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = otherIssueText,
                            onValueChange = onOtherIssueTextChange,
                            label = { Text(text = issue.text) },
                            singleLine = true,
                            enabled = selectedIssueType == IssueType.OTHER
                        )
                    else
                        Text(text = issue.text)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IssueReportScreenPreview() {
    val dummyQuestion = QuizQuestion(
        id = "1",
        topicCode = 1,
        question = "What is the capital of India?",
        allOptions = listOf("New Delhi", "Mumbai", "Kolkata", "Chennai"),
        correctAnswer = "New Delhi",
        explanation = "The capital of India is New Delhi."
    )
    QuizieTheme {
        IssueReportScreen(
            state = IssueReportState(
                quizQuestion = dummyQuestion,
                isExpanded = false
            )
        )
    }
}