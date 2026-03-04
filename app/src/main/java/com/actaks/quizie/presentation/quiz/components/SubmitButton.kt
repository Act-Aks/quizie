package com.actaks.quizie.presentation.quiz.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.actaks.quizie.presentation.theme.QuizieTheme

@Composable
fun SubmitButton(
    isPreviousButtonEnabled: Boolean,
    isNextButtonEnabled: Boolean,
    onPreviousButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedIconButton(
            enabled = isPreviousButtonEnabled,
            onClick = onPreviousButtonClick,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Previous"
            )
        }
        Button(
            modifier = Modifier.padding(horizontal = 24.dp),
            onClick = {}
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = "Submit"
            )
        }
        OutlinedIconButton(
            enabled = isNextButtonEnabled,
            onClick = onNextButtonClick
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Next"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubmitButtonPreview() {
    QuizieTheme {
        SubmitButton(
            modifier = Modifier.fillMaxWidth(),
            isPreviousButtonEnabled = false,
            isNextButtonEnabled = true,
            onPreviousButtonClick = {},
            onNextButtonClick = {}
        )
    }
}