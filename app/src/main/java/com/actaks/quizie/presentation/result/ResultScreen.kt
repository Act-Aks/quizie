package com.actaks.quizie.presentation.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.actaks.quizie.R
import com.actaks.quizie.presentation.theme.QuizieTheme

@Composable
fun ResultScreen(
    state: ResultState
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScoreCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 80.dp, horizontal = 16.dp),
            correctAnswers = state.correctAnswers,
            totalQuestions = state.totalQuestions
        )
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

@Preview(showBackground = true)
@Composable
private fun ResultScreenPreview() {
    QuizieTheme {
        ResultScreen(
            state = ResultState(
                correctAnswers = 7,
                totalQuestions = 10
            )
        )
    }
}