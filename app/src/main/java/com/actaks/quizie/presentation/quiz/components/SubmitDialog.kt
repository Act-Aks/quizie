package com.actaks.quizie.presentation.quiz.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.actaks.quizie.presentation.theme.QuizieTheme

@Composable
fun SubmitDialog(
    isOpen: Boolean,
    modifier: Modifier = Modifier,
    title: String = "Submit Quiz",
    confirmButtonLabel: String = "Submit",
    dismissButtonLabel: String = "Cancel",
    onDismissDialog: () -> Unit,
    onConfirmDialog: () -> Unit,
) {
    if (isOpen)
        AlertDialog(
            modifier = modifier,
            title = { Text(text = title) },
            text = {
                Text(
                    text = "Are you sure, you want to submit this quiz?"
                )
            },
            onDismissRequest = onDismissDialog,
            confirmButton = {
                TextButton(onClick = onConfirmDialog) {
                    Text(text = confirmButtonLabel)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissDialog) {
                    Text(text = dismissButtonLabel)
                }
            }
        )
}

@Preview
@Composable
private fun SubmitDialogPreview() {
    QuizieTheme {
        SubmitDialog(
            isOpen = true,
            onDismissDialog = {},
            onConfirmDialog = {},
        )
    }
}
